package com.micosoft.estoreback.market;

import com.micosoft.estoreback.categories.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "markets")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String logo;
    private Boolean isActive;
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @ManyToMany(mappedBy = "markets")
    private Set<Category> categories = new LinkedHashSet<>();

    @PrePersist
    protected void onCreate(){
        createdAt=ZonedDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = ZonedDateTime.now();
    }
}