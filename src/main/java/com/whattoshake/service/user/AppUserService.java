package com.whattoshake.service.user;

import com.whattoshake.dto.AppUserDTO;
import com.whattoshake.model.enums.Role;
import com.whattoshake.model.user.AppUser;

import java.util.List;
import java.util.Map;

public interface AppUserService {
    boolean save(Map<String, String> userJSON);

    AppUser update(String appUserId, AppUserDTO appUserDTO);

    AppUser findById(String appUserId);

    AppUser findByIdAndRole(String appUserId, Role role);

    List<AppUser> findAll();

    List<AppUser> findAllByRole(Role role);

    void deleteById(String appUserId);
    boolean resetPassword(String username);
}
