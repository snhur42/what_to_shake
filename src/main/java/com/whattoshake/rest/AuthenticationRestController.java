package com.whattoshake.rest;

import com.whattoshake.dto.request.AuthenticationRequestDTO;
import com.whattoshake.dto.request.LogoutRequestDTO;
import com.whattoshake.dto.request.ResetPasswordRequestDTO;
import com.whattoshake.dto.response.AuthAccessJwtResponseDTO;
import com.whattoshake.dto.response.AuthRefreshJwtResponseDTO;
import com.whattoshake.service.auth.AuthenticationService;
import com.whattoshake.service.user.impl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final AppUserServiceImpl appUserService;

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService, AppUserServiceImpl appUserService) {
        this.authenticationService = authenticationService;
        this.appUserService = appUserService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthAccessJwtResponseDTO> authenticate(HttpServletResponse response, @RequestBody AuthenticationRequestDTO request) {
        log.info("Login: " + request.username());
        return authenticationService.authenticate(response, request);
    }

    @PostMapping("logout")
    public void logout(HttpServletResponse response, HttpServletRequest request, @RequestBody LogoutRequestDTO logoutRequestDTO) {
        log.info("Logout : " + logoutRequestDTO.userId() + " " + logoutRequestDTO.fingerPrint());
        authenticationService.logout(response, request, logoutRequestDTO);
    }

    @PostMapping("refresh_token")
    public ResponseEntity<AuthRefreshJwtResponseDTO> updateRefreshToken(@CookieValue(name = "refreshToken", defaultValue = "No cookies") String refreshToken, HttpServletResponse response) {
        return authenticationService.refreshToken(refreshToken, response);
    }

    @PostMapping("reset_password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        return new ResponseEntity<>(appUserService.resetPassword(resetPasswordRequestDTO.username()), HttpStatus.CREATED);
    }
}
