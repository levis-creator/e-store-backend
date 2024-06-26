package com.micosoft.estoreback.categories;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String title;
    private String description;
    private String slug;
    private String imageUrl;
    private Boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updateAt;

}
