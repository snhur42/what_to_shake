package com.whattoshake.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public record RefreshTokenRequestDTO(
        UUID userId,
        String fingerPrint
) {
}
