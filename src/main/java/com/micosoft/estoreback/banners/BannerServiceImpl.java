package com.micosoft.estoreback.banners;

import com.micosoft.estoreback.errors.exceptions.BadRequest;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerServices {
    @Autowired
    private final BannerRepository bannerRepository;

    @Override
    public List<Banner> getBanners() {
        return bannerRepository.findAll();
    }


    @Override
    public Banner createBanner(BannerDTO bannerDTO) {
        Banner banner= Banner.builder()
                .title(bannerDTO.getTitle())
                .url(bannerDTO.getUrl())
                .isPublished(bannerDTO.getIsPublished())
                .build();
        try {
            return bannerRepository.save(banner);
        } catch (Exception e) {
            throw new BadRequest("Banner not saved");
        }
    }

    @Override
    public Banner updateBanner(Long id, BannerDTO bannerDTO) {
        Banner dbBanner=bannerRepository.findById(id).orElseThrow(()->new NotFound("Banner not found"));
        if (!bannerDTO.getTitle().isBlank()||!bannerDTO.getTitle().equals(dbBanner.getTitle())){
            dbBanner.setTitle(bannerDTO.getTitle());
        }
        if (!bannerDTO.getUrl().isBlank()||!bannerDTO.getUrl().equals(dbBanner.getUrl())){
            dbBanner.setUrl(bannerDTO.getUrl());
        }
        if (!bannerDTO.getIsPublished().equals(dbBanner.getIsPublished())){
            dbBanner.setIsPublished(bannerDTO.getIsPublished());
        }
        dbBanner.setLastUpdate();
        return bannerRepository.save(dbBanner);
    }

    @Override
    public void deleteBanner(Long id) {
        Banner banner=bannerRepository.findById(id).orElseThrow(()->new NotFound("Banner not found"));
        bannerRepository.delete(banner);
    }
}
