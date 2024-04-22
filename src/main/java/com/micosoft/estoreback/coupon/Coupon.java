package com.micosoft.estoreback.coupon;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String couponCode;
    private LocalDate expiryDate;
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    private Boolean isActive;
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = LocalDate.parse(expiryDate);
    }
    @PrePersist
    protected void onCreate() {
        createdAt = ZonedDateTime.now();
    }
}