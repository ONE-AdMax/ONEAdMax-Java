package com.oneadmax.global.sample.java.pure;

import com.oneadmax.global.OAMBanner;

public class BannerItem {
    private final OAMBanner banner;
    private final String placementId;
    private final OAMBanner.Size size;

    public BannerItem(OAMBanner banner, String placementId, OAMBanner.Size size) {
        this.banner = banner;
        this.placementId = placementId;
        this.size = size;
    }

    public OAMBanner getBanner() {
        return banner;
    }

    public String getPlacementId() {
        return placementId;
    }

    public OAMBanner.Size getSize() {
        return size;
    }
}
