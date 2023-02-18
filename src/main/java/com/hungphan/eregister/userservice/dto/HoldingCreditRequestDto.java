package com.hungphan.eregister.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoldingCreditRequestDto {

    private Long creditAmount;
    private String username;
    private String description;

}
