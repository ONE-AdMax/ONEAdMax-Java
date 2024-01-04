package com.oneadmax.global.sample.java;

import android.os.Bundle;

import com.oneadmax.global.ONEAdMax;
import com.oneadmax.global.listener.IOAMInitListener;
import com.oneadmax.global.sample.java.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements IOAMInitListener {
    private ActivityMainBinding binding;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUi();
        initONEAdMax();
    }

    protected void initUi() {
        binding.btnBanner.setOnClickListener(view -> {
            showActivity(BannerActivity.class);
        });

        binding.btnInterstitial.setOnClickListener(view -> {
            showActivity(InterstitialActivity.class);
        });

        binding.btnInterstitialVideo.setOnClickListener(view -> {
            showActivity(InterstitialVideoActivity.class);
        });

        binding.btnRewardVideo.setOnClickListener(view -> {
            showActivity(RewardVideoActivity.class);
        });
    }


    private void initONEAdMax() {
        showProgressBar();

        if (BuildConfig.DEBUG)
            ONEAdMax.setLogEnable(true);

        ONEAdMax.init(this, this);
    }

    @Override
    public void onInitialized() {
        hideProgressBar();
        showToast("ONEAdMax SDK Init Completed.");
    }

    public void onDestroy() {
        super.onDestroy();
        ONEAdMax.unInit();
    }
}