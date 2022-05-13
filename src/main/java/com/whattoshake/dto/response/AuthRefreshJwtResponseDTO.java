package com.whattoshake.dto.response;

import lombok.*;

public record AuthRefreshJwtResponseDTO(
        boolean success,
        String accessToken
) {

}