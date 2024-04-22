package com.micosoft.estoreback.coupon;

import com.micosoft.estoreback.errors.exceptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
    @Autowired
    private final CouponServices couponServices;
    @GetMapping
    ResponseEntity<?> fetchingCoupons(){
        List<CouponDTO> couponData= couponServices.getCoupons();
        return new  ResponseEntity<>(couponData, HttpStatus.OK) ;
    }
    @PostMapping
    ResponseEntity<?> creatingCoupons(@RequestBody CouponDTO couponDTO){
        Coupon newCoupon=couponServices.createCoupon(couponDTO);
        return new ResponseEntity<>(newCoupon, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updateCoupon(@RequestBody CouponDTO couponDTO, @PathVariable Long id){
        CouponDTO updatCoupon=couponServices.updateCoupon(couponDTO, id);
        return new ResponseEntity<>(updatCoupon, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deleteCoupon(@PathVariable Long id){
        try {
            couponServices.deleteCoupon(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);
        }catch (NotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
