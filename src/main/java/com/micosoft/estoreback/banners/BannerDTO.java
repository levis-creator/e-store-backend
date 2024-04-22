package com.micosoft.estoreback.banners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class BannerDTO {
    private Long id;
    private String title;
    private String url;
    private Boolean isPublished;
    private ZonedDateTime createdAt;
}
