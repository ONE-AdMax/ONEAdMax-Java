package com.oneadmax.global.sample.java;

import android.os.Bundle;
import android.util.Log;

import com.oneadmax.global.OAMBanner;
import com.oneadmax.global.OAMError;
import com.oneadmax.global.listener.IOAMBannerEventListener;
import com.oneadmax.global.sample.java.databinding.ActivityBannerBinding;

public class BannerActivity extends BaseActivity implements IOAMBannerEventListener {
    private ActivityBannerBinding binding;
    String[] bannerPID = { "ONESTORE_BANNER_320x50",
                           "ONESTORE_BANNER_300x250",
                           "ONESTORE_BANNER_320x100" };

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBanner();
    }

    void initBanner() {
        initBanner(binding.banner320x50,  bannerPID[0], OAMBanner.Size.BANNER_320x50);
        initBanner(binding.banner300x250, bannerPID[1], OAMBanner.Size.BANNER_300x250);
        initBanner(binding.banner320x100, bannerPID[2], OAMBanner.Size.BANNER_320x100);
    }

    void initBanner(OAMBanner banner, String placementId, OAMBanner.Size size) {
        banner.setPlacementId(placementId);
        banner.setSize(size);
        banner.setAnimType(OAMBanner.AnimType.SLIDE_LEFT);
        banner.setNetworkScheduleTimeout(10);
        banner.setRefreshTime(15);
        banner.setEventListener(this);
        banner.load();
    }

    @Override
    public void onLoaded() {
        Log.d(TAG, "banner load success");
    }

    @Override
    public void onLoadFailed(OAMError error) {
        showToast("onLoadFailed : " + error.toString());
    }

    @Override
    public void onClicked() {
        showToast("onClicked()");
    }

    @Override
    public void onPause() {
        super.onPause();

        binding.banner320x50.onPause();
        binding.banner300x250.onPause();
        binding.banner320x100.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.banner320x50.onResume();
        binding.banner300x250.onResume();
        binding.banner320x100.onResume();
    }
}