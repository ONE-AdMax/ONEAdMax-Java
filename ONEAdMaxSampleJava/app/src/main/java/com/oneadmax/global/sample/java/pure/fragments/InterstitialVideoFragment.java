package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMInterstitialVideo;
import com.oneadmax.global.listener.IOAMInterstitialVideoEventListener;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentFullScreenContentBinding;

public class InterstitialVideoFragment extends BaseFragment<FragmentFullScreenContentBinding> implements IOAMInterstitialVideoEventListener {

    private OAMInterstitialVideo interstitialVideoAd;
    private boolean isOpened = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFullScreenContentBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        showProgressDialog();

        interstitialVideoAd = generateInterstitialVideoAd();
        interstitialVideoAd.load();

        binding.btn.setOnClickListener(v -> {
            if (!isOpened && interstitialVideoAd.isLoaded()) {
                interstitialVideoAd.show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (interstitialVideoAd != null) {
            interstitialVideoAd.destroy();
        }
    }

    // add Your App's Placement ID
    protected String getPlacementId() {
        return PlacementIds.INTERSTITIAL_VIDEO_ID;
    }

    private OAMInterstitialVideo generateInterstitialVideoAd() {
        // Create an interstitial video ad
        final OAMInterstitialVideo ad = new OAMInterstitialVideo(requireContext());
        ad.setPlacementId(getPlacementId());
        ad.setEventListener(this);

        return ad;
    }

    // =============================================================================================
    // implements IOAMInterstitialVideoEventListener
    // =============================================================================================

    @Override
    public void onLoaded() {
        dismissProgressDialog();
        addMethodName(null);
    }

    @Override
    public void onLoadFailed(OAMError error) {
        dismissProgressDialog();
        addMethodName(error.toString());
    }

    @Override
    public void onOpened() {
        isOpened = true;
        addMethodName(null);
    }

    @Override
    public void onOpenFailed(OAMError error) {
        isOpened = false;
        addMethodName(error.toString());
    }

    @Override
    public void onClosed() {
        isOpened = false;
        addMethodName(null);
        showProgressDialog();
        // retry loading the ad
        interstitialVideoAd.load();
    }

    @Override
    public void onClicked() {
        addMethodName(null);
    }
}
