package com.micosoft.estoreback.categories;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Builder
@Setter

public class CategoryDTO {
    private Long id;
    private String title;
    private String description;
    private String slug;
    private String imageUrl;
    private Boolean status;
    private ZonedDateTime createdAt;
}
