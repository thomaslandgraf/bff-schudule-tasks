package com.landgraf.bffscheduletasks.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {

    private String email;
    private String password;
}

