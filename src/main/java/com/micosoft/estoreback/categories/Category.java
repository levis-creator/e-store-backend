package com.micosoft.estoreback.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micosoft.estoreback.market.Market;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder

@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;

    @Column(unique = true)
    private String slug;
    private String imageUrl;
    private Boolean status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updateAt;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "category_markets",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "markets_id"))
    @JsonIgnore
    private Set<Market> markets = new LinkedHashSet<>();

}