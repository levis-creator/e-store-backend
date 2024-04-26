package com.micosoft.estoreback.market;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class MarketDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String title;
    private String logo;
    private Boolean isActive;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;
}
