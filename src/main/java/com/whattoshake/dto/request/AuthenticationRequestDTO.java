package com.whattoshake.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public record AuthenticationRequestDTO(
        //login or email
        String username,
        String password,
        String fingerPrint
) {

}
