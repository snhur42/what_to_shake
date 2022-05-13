package com.whattoshake.service.auth.refresh_token;



import com.whattoshake.model.refresh_token.RefreshToken;

import java.util.List;
import java.util.UUID;


public interface RefreshTokenService {
    RefreshToken save(RefreshToken refreshToken);

    RefreshToken findById(UUID id);

    List<RefreshToken> findAll();

    void deleteAllByUserId(String userId);

    void delete(RefreshToken refreshToken);

}
