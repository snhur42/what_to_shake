package com.whattoshake.service.user.impl;

import com.whattoshake.dto.AppUserDTO;
import com.whattoshake.model.enums.Role;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.repository.user.UserRepository;
import com.whattoshake.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Qualifier("appUserServiceImpl")
public class AppUserServiceImpl implements AppUserService {
    protected final PasswordEncoder passwordEncoder;
    protected final UserRepository repository;


    public AppUserServiceImpl(PasswordEncoder passwordEncoder,
                              UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public boolean save(Map<String, String> userJSON) {
        AppUser appUser = new AppUser();

        appUser.setFirstAndLastName(userJSON.get("firstAndLastName"));
        appUser.setLogin(userJSON.get("login"));
        appUser.setEmail(userJSON.get("email"));
        appUser.setRole(Role.valueOf(userJSON.get("role")));
        appUser.setPassword(passwordEncoder.encode(userJSON.get("password")));
        appUser.setAccountNonExpired(true);
        appUser.setCredentialsNonExpired(true);
        appUser.setAccountNonLocked(true);
        appUser.setEnabled(true);

        repository.save(appUser);

        return true;
    }

    @Override
    public AppUser update(String appUserId, AppUserDTO appUserDTO) {
        return null;
    }

    @Override
    public AppUser findById(String appUserId) {
        return null;
    }

    @Override
    public AppUser findByIdAndRole(String appUserId, Role role) {
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public List<AppUser> findAllByRole(Role role) {
        return null;
    }

    @Override
    public void deleteById(String appUserId) {

    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }
}
