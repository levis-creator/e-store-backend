package com.micosoft.estoreback.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductInputDTO {
    private String title;
    private String description;
    private String slug;
    private List<String> images;
    private String sku;
    private String barcode;
    private Long productPrice;
    private Long salesPrice;
    private List<String> tags;
    private Boolean isPublished;
    private Long category;
    private Long farmer;
}
