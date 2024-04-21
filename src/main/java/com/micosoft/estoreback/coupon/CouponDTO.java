package com.micosoft.estoreback.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponDTO {
    private Long id;
    private String title;
    private String couponCode;
    private Boolean isActive;
    private String expiryDate;
    private String createdAt;
}
