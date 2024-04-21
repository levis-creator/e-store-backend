package com.micosoft.estoreback.coupon;

import java.util.List;

public interface CouponServices {
    Coupon createCoupon(CouponDTO couponDTO);
    List<CouponDTO>getCoupons();
    CouponDTO updateCoupon(CouponDTO couponDTO, Long id);
    void deleteCoupon(Long id);
}
