package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMRewardVideo;
import com.oneadmax.global.listener.IOAMRewardVideoEventListener;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentFullScreenContentBinding;

public class RewardVideoFragment extends BaseFragment<FragmentFullScreenContentBinding> implements IOAMRewardVideoEventListener {

    private OAMRewardVideo rewardVideoAd;
    private boolean isOpened = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFullScreenContentBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        showProgressDialog();

        rewardVideoAd = generateRewardVideoAd();
        rewardVideoAd.load();

        //  Show the ad when the button is clicked
        binding.btn.setOnClickListener(v -> {
            if (!isOpened && rewardVideoAd.isLoaded()) {
                rewardVideoAd.show();
            }
        });
        return binding.getRoot();
    }

    // Insert the placement ID
    protected String getPlacementId() {
        return PlacementIds.REWARD_VIDEO_ID;
    }

    private OAMRewardVideo generateRewardVideoAd() {
        // Create a new OAMRewardVideo instance
        final OAMRewardVideo ad = new OAMRewardVideo(requireContext());
        ad.setPlacementId(getPlacementId());
        ad.setEventListener(this);

        return ad;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rewardVideoAd.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Call the onResume method of the OAMRewardVideo instance
        if (rewardVideoAd != null) rewardVideoAd.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Call the onPause method of the OAMRewardVideo instance
        if (rewardVideoAd != null) rewardVideoAd.onPause();
    }


    // =============================================================================================
    // implements IOAMRewardVideoEventListener
    // =============================================================================================

    /**
     * Called when the ad is loaded successfully
     */
    @Override
    public void onLoaded() {
        dismissProgressDialog();
        addMethodName(null);
    }

    /**
     * Called when the ad fails to load
     * @param error
     */
    @Override
    public void onLoadFailed(OAMError error) {
        dismissProgressDialog();
        addMethodName(error.toString());
    }

    /**
     * Called when the ad is opened
     */
    @Override
    public void onOpened() {
        isOpened = true;
        addMethodName(null);
    }

    /**
     * Called when the ad fails to open
     * @param error
     */
    @Override
    public void onOpenFailed(OAMError error) {
        isOpened = false;
        addMethodName(error.toString());
    }

    /**
     * Called when the ad is closed
     */
    @Override
    public void onClosed() {
        isOpened = false;
        addMethodName(null);
        showProgressDialog();
        rewardVideoAd.load();
    }

    /**
     *  Called When user completed the video
     * @param adNetworkNo
     * @param completed  true: ad completed, false: ad canceled
     */
    @Override
    public void onCompleted(int adNetworkNo, boolean completed) {
        addMethodName("adNetworkNo: " + adNetworkNo + ", completed: " + completed);
    }

    /**
     * Called when the ad is clicked
     */
    @Override
    public void onClicked() {
        addMethodName(null);
    }
}
