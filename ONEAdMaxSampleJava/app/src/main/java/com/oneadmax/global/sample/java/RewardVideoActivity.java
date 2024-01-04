package com.oneadmax.global.sample.java;

import android.os.Bundle;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMRewardVideo;
import com.oneadmax.global.listener.IOAMRewardVideoEventListener;
import com.oneadmax.global.sample.java.databinding.ActivityRewardVideoBinding;

public class RewardVideoActivity extends BaseActivity implements IOAMRewardVideoEventListener {
    private ActivityRewardVideoBinding binding;
    private OAMRewardVideo rewardVideo;
    String rewardVideoPID = "ONESTORE_REWARD_VIDEO";

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRewardVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUi();
    }

    protected void initUi() {
        binding.btnClean.setOnClickListener(view -> {
            initRewardVideo(rewardVideoPID);
        });
    }

    void initRewardVideo(String placementId) {
        showProgressBar();

        rewardVideo = new OAMRewardVideo(this);
        rewardVideo.setPlacementId(placementId);
        rewardVideo.setEventListener(this);
        rewardVideo.load();
    }

    @Override
    public void onLoaded() {
        hideProgressBar();
        rewardVideo.show();
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
        showToast("onOpenFailed()");
    }

    @Override
    public void onClosed() {
        showToast("onClosed()");
    }

    @Override
    public void onCompleted(int networkId, boolean completed) {
        showToast("onCompleted : networkID( " + networkId +
                " ), completed( " + (completed ? "true" : "false") + " )");
    }

    @Override
    public void onClicked() {
        showToast("onClicked");
    }
}