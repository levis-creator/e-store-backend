package com.micosoft.estoreback.categories;

import com.micosoft.estoreback.market.Market;
import com.micosoft.estoreback.market.MarketDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@Builder

public class CategoryInputDTO {
    private String title;
    private String description;
    private String slug;
    private String imageUrl;
    private Boolean status;
    private Set<Long> markets = new LinkedHashSet<>();
}
