package com.whattoshake.repository.refresh_token;

import com.whattoshake.model.refresh_token.RefreshToken;
import com.whattoshake.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends EntityRepository<RefreshToken> {
    Optional<RefreshToken> findByUserIdAndFingerPrint(String userId, String fingerPrint);
    List<RefreshToken> findAllByUserId(String userId);
    void deleteAllByUserId(String userId);

}