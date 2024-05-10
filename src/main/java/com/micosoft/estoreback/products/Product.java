package com.micosoft.estoreback.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micosoft.estoreback.categories.Category;
import com.micosoft.estoreback.farmers.Farmer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Column(unique = true)
    private String slug;
    private List<String> images;
    private String sku;
    private String barcode;
    private Float productPrice;
    private Float salesPrice;
    private List<String> tags;
    private Boolean isPublished;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;

    private Boolean isWholeSale;
    private  Long wholeSalePrice;
    private Long minWholeSaleQuantity;
    private  String unit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    private Long qty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

}