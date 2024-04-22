package com.micosoft.estoreback.banners;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/banners")
public class BannerController {
    @Autowired
    private final BannerServices bannerServices;
    @GetMapping
    ResponseEntity<List<Banner>> getBanner(){
        List<Banner> banners=bannerServices.getBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }
//    TODO: Add validation to prevent null data to all create requests
    @PostMapping
    ResponseEntity<?> createBanner(@RequestBody BannerDTO bannerDTO){
        Banner createdBanner= bannerServices.createBanner(bannerDTO);
        return new ResponseEntity<>(createdBanner, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<?> updatingBanner(@RequestBody BannerDTO bannerDTO,@PathVariable Long id ){
        Banner updateBanner= bannerServices.updateBanner(id, bannerDTO);
        return  new ResponseEntity<>(updateBanner, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> deletingBanner(@PathVariable Long id){
        bannerServices.deleteBanner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
