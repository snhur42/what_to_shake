package com.whattoshake.security.jwt.provider.impl;


import com.whattoshake.service.user.impl.AppUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("jwtAccessTokenProvider")
public class JwtAccessTokenProvider extends JwtTokenProviderImpl {

    @Autowired
    public JwtAccessTokenProvider(@Qualifier("appUserDetailsServiceImpl") AppUserDetailsServiceImpl appUserDetailsService,
                                  @Value("${auth.jwt.secret.access.token}") String secretKey,
                                  @Value("${auth.jwt.expiration.access.token}") long validityInMilliseconds) {
        super(appUserDetailsService, secretKey, validityInMilliseconds);
    }
}
