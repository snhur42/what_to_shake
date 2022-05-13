package com.whattoshake.security.jwt.provider.impl;

import com.whattoshake.model.user.AppUser;
import com.whattoshake.security.jwt.provider.JwtTokenProvider;
import com.whattoshake.service.user.impl.AppUserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
public abstract class JwtTokenProviderImpl implements JwtTokenProvider {

    protected final long validityInMilliseconds;
    private final AppUserDetailsServiceImpl appUserDetailsService;
    @Value("${auth.header}")
    protected String authorizationHeader;
    @Value("${auth.header.prefix}")
    protected String authorizationHeaderPrefix;
    protected String secretKey;

    public JwtTokenProviderImpl(AppUserDetailsServiceImpl appUserDetailsService,
                                String secretKey,
                                long validityInMilliseconds) {
        this.appUserDetailsService = appUserDetailsService;
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String createToken(String userId, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);


        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Authentication getAuthentication(String token) {
        AppUser appUser = this.appUserDetailsService.loadUserById(getSubject(token));

        return new UsernamePasswordAuthenticationToken(appUser, appUser.getAuthorities(), appUser.getAuthorities());
    }

    @Override
    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Date getExpiredDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    @Override
    public boolean IsExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody().getExpiration().after(new Date());
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        String headerValue = request.getHeader(authorizationHeader);
        if (headerValue != null && headerValue.startsWith(authorizationHeaderPrefix)) {
            return headerValue.substring(authorizationHeaderPrefix.length());
        }
        return null;
    }
}
