package com.micosoft.estoreback.training;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingInputDTO {
    private String title;
    private String slug;
    private String description;
    private String content;
    private Long categories;
    private String thumbnail;
    private Boolean isPublished;
}
