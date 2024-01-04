package com.oneadmax.global.sample.java;

import android.os.Bundle;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMInterstitial;
import com.oneadmax.global.enums.OAMCloseEvent;
import com.oneadmax.global.listener.IOAMInterstitialEventListener;
import com.oneadmax.global.sample.java.databinding.ActivityInterstitialBinding;

public class InterstitialActivity extends BaseActivity implements IOAMInterstitialEventListener {
    private ActivityInterstitialBinding binding;
    private OAMInterstitial interstital;
    String interstitialPID = "ONESTORE_INTERSTITIAL";

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInterstitialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUi();
    }

    protected void initUi() {
        binding.btnClean.setOnClickListener(view -> {
            initInterstitial(interstitialPID);
        });
    }

    void initInterstitial(String placementId) {
        showProgressBar();

        interstital = new OAMInterstitial(this);
        interstital.setPlacementId(placementId);
        interstital.setEventListener(this);
        interstital.load();
    }


    @Override
    public void onLoaded() {
        hideProgressBar();
        interstital.show();
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
    public void onClosed(OAMCloseEvent event) {
        showToast("onClosed : " + event.toString());
    }

    @Override
    public void onClicked() {
        showToast("onClicked()");
    }
}