package com.whattoshake.service.auth;

import com.whattoshake.dto.AppUserDTO;
import com.whattoshake.dto.request.AuthenticationRequestDTO;
import com.whattoshake.dto.request.LogoutRequestDTO;
import com.whattoshake.dto.response.AuthAccessJwtResponseDTO;
import com.whattoshake.dto.response.AuthRefreshJwtResponseDTO;
import com.whattoshake.model.refresh_token.RefreshToken;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.security.jwt.provider.impl.JwtAccessTokenProvider;
import com.whattoshake.security.jwt.provider.impl.JwtRefreshTokenProvider;
import com.whattoshake.service.auth.refresh_token.RefreshTokenServiceImpl;
import com.whattoshake.service.user.impl.AppUserDetailsServiceImpl;
import com.whattoshake.service.user.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import org.springframework.security.core.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Qualifier("authenticationService")
public record AuthenticationService(
        AuthenticationManager authenticationManager,
        AppUserDetailsServiceImpl appUserDetailsService,
        AppUserServiceImpl appUserService,
        RefreshTokenServiceImpl refreshTokenServiceImpl,
        JwtAccessTokenProvider jwtAccessTokenProvider,
        JwtRefreshTokenProvider jwtRefreshTokenProvider) {
    @Autowired
    public AuthenticationService {
    }

    public ResponseEntity<AuthAccessJwtResponseDTO> authenticate(HttpServletResponse response, AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));



            AppUser user = appUserDetailsService.loadUserByUsername(request.username());

            String accessTokenString = jwtAccessTokenProvider.createToken(
                    user.getId().toString(),
                    user.getRole().toString()
            );

            String refreshTokenString = jwtRefreshTokenProvider.createToken(
                    user.getId().toString(),
                    user.getRole().toString()
            );

            this.countRefreshTokens(user.getId().toString());

            RefreshToken refreshTokenObj = this.saveRefreshToken(user.getId().toString(), refreshTokenString, request.fingerPrint(), jwtRefreshTokenProvider.getExpiredDate(refreshTokenString));


            ResponseCookie resCookie = ResponseCookie.from("refreshToken", refreshTokenObj.getId().toString())
                    .httpOnly(true)
                    .secure(true)
                    .path("/api/auth")
                    .maxAge(30 * 24 * 60 * 60)
                    .domain("localhost")
                    .build();


            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.SET_COOKIE, resCookie.toString());

            log.info("Login success: " + request.username());

            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .body(new AuthAccessJwtResponseDTO(true, accessTokenString, new AppUserDTO(
                            user.getId().toString(),
                            user.getFirstAndLastName(),
                            user.getLogin(),
                            user.getEmail(),
                            user.getRole()
                    )));


        } catch (AuthenticationException e) {
            log.error("Invalid email/password combination " + request.username());
            return ResponseEntity
                    .ok()
                    .body(new AuthAccessJwtResponseDTO(false, null, null));
        }
    }


    public ResponseEntity<AuthRefreshJwtResponseDTO> refreshToken(String refreshTokenId, HttpServletResponse response) {
        try {
            RefreshToken refreshToken = refreshTokenServiceImpl.findById(UUID.fromString(refreshTokenId));

            if (jwtRefreshTokenProvider.IsExpired(refreshToken.getRefreshToken())) {
                AppUser user = appUserService.findById(refreshToken.getUserId());

                String accessTokenString = jwtAccessTokenProvider.createToken(
                        user.getId().toString(),
                        user.getRole().toString()
                );

                String refreshTokenString = jwtRefreshTokenProvider.createToken(
                        user.getId().toString(),
                        user.getRole().toString()
                );

                String fingerPrint = refreshToken.getFingerPrint();
                refreshTokenServiceImpl.delete(refreshToken);

                RefreshToken refreshTokenObj = this.saveRefreshToken(user.getId().toString(), refreshTokenString, fingerPrint, jwtRefreshTokenProvider.getExpiredDate(refreshTokenString));

                ResponseCookie resCookie = ResponseCookie.from("refreshToken", refreshTokenObj.getId().toString())
                        .httpOnly(true)
                        .secure(true)
                        .path("/api/auth")
                        .maxAge(30 * 24 * 60 * 60)
                        .domain("localhost")
                        .build();


                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set(HttpHeaders.SET_COOKIE, resCookie.toString());

                log.info("New refresh token: " + refreshTokenString);

                return ResponseEntity
                        .ok()
                        .headers(responseHeaders)
                        .body(new AuthRefreshJwtResponseDTO(true, accessTokenString));
            } else {
                return ResponseEntity
                        .ok()
                        .body(new AuthRefreshJwtResponseDTO(false, null));
            }


        } catch (AuthenticationException e) {
            return ResponseEntity
                    .ok()
                    .body(new AuthRefreshJwtResponseDTO(false, null));
        }


    }

    public void logout(HttpServletResponse response, HttpServletRequest request, LogoutRequestDTO logoutRequestDTO) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        try {
            refreshTokenServiceImpl.delete(
                    refreshTokenServiceImpl.findByUserIdAndFingerPrint(
                            logoutRequestDTO.userId(),
                            logoutRequestDTO.fingerPrint()
                    )
            );
        } catch (Exception err) {
            log.error("logout error " + err.getMessage());
        }

    }

    private void countRefreshTokens(String userId) {
        long countRefreshToken = refreshTokenServiceImpl.findAll().stream().filter(token -> token.getUserId().equals(userId)).count();

        if (countRefreshToken >= 4) {
            refreshTokenServiceImpl.deleteAllByUserId(userId);
        }
    }

    private RefreshToken saveRefreshToken(String userId, String refreshTokenString, String fingerPrint, Date expiredDate) {
        return refreshTokenServiceImpl.save(new RefreshToken(
                userId,
                refreshTokenString,
                fingerPrint,
                expiredDate
        ));
    }

}
