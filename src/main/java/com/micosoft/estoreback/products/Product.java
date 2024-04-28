package com.micosoft.estoreback.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micosoft.estoreback.categories.Category;
import com.micosoft.estoreback.farmers.Farmer;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
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
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @PrePersist
    protected void onCreate(){
        createdAt = ZonedDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = ZonedDateTime.now();
    }
}