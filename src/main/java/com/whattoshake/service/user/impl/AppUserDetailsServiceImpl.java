package com.whattoshake.service.user.impl;

import com.whattoshake.exception.model.UserNotFoundException;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Qualifier("appUserDetailsServiceImpl")
public class AppUserDetailsServiceImpl implements UserDetailsService {

    protected final UserRepository repository;


    public AppUserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> appUser = repository.findByLogin(username);

        if (appUser.isPresent()) {
            return appUser.get();
        } else {
            throw new UserNotFoundException("There is not User with this username/login: " + username);
        }
    }


    public AppUser loadUserById(String appUserId) throws UsernameNotFoundException {
        Optional<AppUser> appUser = repository.findById(UUID.fromString(appUserId));

        if (appUser.isPresent()) {
            return appUser.get();
        } else {
            throw new UserNotFoundException("There is not User with this id: " + appUserId);
        }
    }
}
