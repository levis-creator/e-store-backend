package com.micosoft.estoreback.banners;

import java.util.List;

public interface BannerServices {
    List<Banner>getBanners();
    Banner createBanner(BannerDTO bannerDTO);
    Banner updateBanner(Long id, BannerDTO bannerDTO);
    void deleteBanner(Long id);
}
