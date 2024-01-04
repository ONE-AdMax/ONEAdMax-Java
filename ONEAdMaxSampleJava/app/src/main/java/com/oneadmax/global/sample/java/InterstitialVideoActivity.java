package com.oneadmax.global.sample.java;

import android.os.Bundle;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMInterstitialVideo;
import com.oneadmax.global.listener.IOAMInterstitialVideoEventListener;
import com.oneadmax.global.sample.java.databinding.ActivityInterstitialVideoBinding;

public class InterstitialVideoActivity extends BaseActivity implements IOAMInterstitialVideoEventListener {
    private ActivityInterstitialVideoBinding binding;
    private OAMInterstitialVideo interstitalVideo;
    String interstitialVideoPID = "ONESTORE_VIDEO";

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInterstitialVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUi();
    }

    protected void initUi() {
        binding.btnClean.setOnClickListener(view -> {
            initInterstitialVideo(interstitialVideoPID);
        });
    }

    void initInterstitialVideo(String placementId) {
        showProgressBar();

        interstitalVideo = new OAMInterstitialVideo(this);
        interstitalVideo.setPlacementId(placementId);
        interstitalVideo.setEventListener(this);
        interstitalVideo.load();
    }

    @Override
    public void onLoaded() {
        hideProgressBar();
        interstitalVideo.show();
    }

    @Override
    public void onLoadFailed(OAMError error) {
        showToast("onLoadFailed : " + error.toString());
    }

    @Override
    public void onOpened() {
        showToast("onOpened()");
    }

    @Override
    public void onOpenFailed(OAMError error) {
        showToast("onOpenFailed : " + error.toString());
    }

    @Override
    public void onClosed() {
        showToast("onClosed()");
    }

    @Override
    public void onClicked() {
        showToast("onClicked()");
    }
}