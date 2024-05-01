package com.micosoft.estoreback.training;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micosoft.estoreback.categories.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor

@Table(name = "training")
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String title;
    private String slug;
    private String description;
    private String content;
    private String thumbnail;
    private Boolean isPublished;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @PrePersist
    protected void onCreate(){
        createdAt = ZonedDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = ZonedDateTime.now();
    }
}