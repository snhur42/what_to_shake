package com.whattoshake.security.jwt.provider.impl;

import com.whattoshake.service.user.impl.AppUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("jwtRefreshTokenProvider")
public class JwtRefreshTokenProvider extends JwtTokenProviderImpl {

    @Autowired
    public JwtRefreshTokenProvider(@Qualifier("appUserDetailsServiceImpl") AppUserDetailsServiceImpl appUserDetailsService,
                                   @Value("${auth.jwt.secret.refresh.token}") String secretKey,
                                   @Value("${auth.jwt.expiration.refresh.token}") long validityInMilliseconds) {
        super(appUserDetailsService, secretKey, validityInMilliseconds);
    }
}