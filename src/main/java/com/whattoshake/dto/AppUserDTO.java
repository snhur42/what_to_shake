package com.whattoshake.dto;

import com.whattoshake.model.enums.Role;

public record AppUserDTO(
        String id,
        String firstAndLastName,
        String login,
        String email,
        Role role
) {

}
