package com.whattoshake.repository.user;

import com.whattoshake.model.enums.Role;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends EntityRepository<AppUser> {
    Optional<AppUser> findByLogin(String login);

    List<AppUser> findAllByRole(Role role);

    Optional<AppUser> findByIdAndRole(UUID appUserId, Role role);
}
