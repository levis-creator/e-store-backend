package com.micosoft.estoreback.farmers;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class FarmerDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String physicalAddress;
    private String contactPerson;
    private String contactPersonPhone;
    private String terms;
    private String notes;
    private Boolean isActive;
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;
}
