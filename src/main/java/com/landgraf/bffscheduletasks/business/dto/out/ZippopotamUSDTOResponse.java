package com.landgraf.bffscheduletasks.business.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZippopotamUSDTOResponse {

    @JsonProperty("post code")
    private String postCode;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    @JsonProperty("places")
    private ArrayList<PlaceDTOResponse> placeDTOS;
}
