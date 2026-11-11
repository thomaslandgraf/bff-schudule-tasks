package com.landgraf.bffscheduletasks.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberDTOResponse {

    private Long id;
    private String number;
}
