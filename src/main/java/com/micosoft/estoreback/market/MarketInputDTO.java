package com.micosoft.estoreback.market;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class MarketInputDTO {
    private String title;
    private String logo;
    private Boolean isActive;
    private Set<Long> categories;
}
