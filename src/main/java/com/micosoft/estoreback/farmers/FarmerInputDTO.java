package com.micosoft.estoreback.farmers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FarmerInputDTO {
    private String name;
    private String phone;
    private String email;
    private String physicalAddress;
    private String contactPerson;
    private String contactPersonPhone;
    private String terms;
    private String notes;
    private Boolean isActive;
}
