package com.micosoft.estoreback.coupon;

import com.micosoft.estoreback.errors.exceptions.BadRequest;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CouponImpl implements CouponServices {
    @Autowired
    private final CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(CouponDTO couponDTO) {
        Coupon coupon = Coupon.builder().title(couponDTO.getTitle()).couponCode(couponDTO.getCouponCode()).isActive(couponDTO.getIsActive()).expiryDate(LocalDate.parse(couponDTO.getExpiryDate())).build();
//        coupon.setCreatedAt();
        return couponRepository.save(coupon);
    }

    @Override
    public List<CouponDTO> getCoupons() {
        List<CouponDTO> couponDTOList = new ArrayList<>();
        List<Coupon> couponList = couponRepository.findAll();
        for (Coupon coupon : couponList) {
            CouponDTO couponDTO = CouponDTO.builder().id(coupon.getId()).title(coupon.getTitle()).couponCode(coupon.getCouponCode()).isActive(coupon.getIsActive()).expiryDate(String.valueOf(coupon.getExpiryDate()))
                    .createdAt(String.valueOf(coupon.getCreatedAt())).build();
            couponDTOList.add(couponDTO);
        }
        return couponDTOList;
    }

    @Override
    public CouponDTO updateCoupon(CouponDTO couponDTO, Long id) {
        Coupon oldCoupon = couponRepository.findById(id).orElseThrow(() -> new NotFound("Coupon Not Found"));

        if (!oldCoupon.getTitle().equals(couponDTO.getTitle()) || !couponDTO.getTitle().isBlank()) {
            oldCoupon.setTitle(couponDTO.getTitle());
        }
        if (!oldCoupon.getIsActive().equals(couponDTO.getIsActive())) {
            oldCoupon.setIsActive(couponDTO.getIsActive());
        }
        if (!oldCoupon.getExpiryDate().equals(LocalDate.parse( couponDTO.getExpiryDate())) || !couponDTO.getExpiryDate().isBlank()) {
            oldCoupon.setExpiryDate(couponDTO.getExpiryDate());
        }
        couponRepository.save(oldCoupon);
        return couponDTO;
    }


    @Override
    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)){
            throw new NotFound("Coupon Not Found");
        }
        try {
        couponRepository.deleteById(id);
        }catch (Exception e){
            throw new BadRequest("Failed to delete");
        }
    }
}
