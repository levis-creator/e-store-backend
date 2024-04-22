package com.micosoft.estoreback.banners;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banners")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String url;
    private Boolean isPublished;
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdate;
    @PrePersist
    protected void onCreate(){
        createdAt=ZonedDateTime.now();
    }

    public void setLastUpdate() {
        this.lastUpdate = ZonedDateTime.now();
    }
}