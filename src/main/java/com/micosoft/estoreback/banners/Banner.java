package com.micosoft.estoreback.banners;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @Column( updatable = false)
    @UpdateTimestamp
    private ZonedDateTime lastUpdate;



}