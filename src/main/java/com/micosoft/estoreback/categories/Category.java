package com.micosoft.estoreback.categories;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private String slug;
    private String imageUrl;
    private Boolean status;
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    private ZonedDateTime updateAt;
    @PrePersist
    protected void created(){
        createdAt=ZonedDateTime.now();
    }

    public void setUpdateAt() {
        this.updateAt = ZonedDateTime.now();
    }
}