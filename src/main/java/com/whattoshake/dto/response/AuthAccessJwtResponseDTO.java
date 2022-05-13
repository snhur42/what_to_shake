package com.whattoshake.dto.response;

import com.whattoshake.dto.AppUserDTO;

public record AuthAccessJwtResponseDTO(
        boolean success,
        String accessToken,
        AppUserDTO user
) {

}