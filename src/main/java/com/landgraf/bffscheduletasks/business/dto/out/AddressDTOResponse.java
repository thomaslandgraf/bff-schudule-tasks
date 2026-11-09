package com.landgraf.bffscheduletasks.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String streetLine1;
    private String streetLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
