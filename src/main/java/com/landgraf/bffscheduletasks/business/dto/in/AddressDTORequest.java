package com.landgraf.bffscheduletasks.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTORequest {

    private String streetLine1;
    private String streetLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
