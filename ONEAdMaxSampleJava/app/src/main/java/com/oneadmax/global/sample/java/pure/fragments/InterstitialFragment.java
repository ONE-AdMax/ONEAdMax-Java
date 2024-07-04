package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMInterstitial;
import com.oneadmax.global.enums.OAMCloseEvent;
import com.oneadmax.global.listener.IOAMInterstitialEventListener;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentFullScreenContentBinding;

public class InterstitialFragment extends BaseFragment<FragmentFullScreenContentBinding> implements IOAMInterstitialEventListener {

    private OAMInterstitial interstitialAd;
    private boolean isOpened = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFullScreenContentBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        showProgressDialog();

        interstitialAd = generateInterstitialAd();
        interstitialAd.load();

        binding.btn.setOnClickListener(v -> {
            if (!isOpened && interstitialAd.isLoaded()) {
                interstitialAd.show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
    }

    protected String getPlacementId() {
        return PlacementIds.INTERSTITIAL_ID;
    }

    private OAMInterstitial generateInterstitialAd() {
        final OAMInterstitial ad = new OAMInterstitial(requireContext());
        ad.setPlacementId(getPlacementId());
        ad.setEventListener(this);
        /*
        final HashMap<String, Object> map = new HashMap<>();
        // Interstitial ad Background Color and Transparency Change
        map.put(CustomExtraData.BACKGROUND_COLOR, Color.TRANSPARENT);
        // Show or hide the close button in the upper right corner of the interstitial ad
        map.put(CustomExtraData.HIDE_CLOSE_BTN, false);
        // Disable back key  for interstitial ad
        map.put(CustomExtraData.DISABLE_BACK_BTN, false);
        //  if you want to set the margin from the center of the ad image, set it to false
        map.put(CustomExtraData.CLOSE_BTN_MARGIN_FROM_EDGE, false);
        // Interstitial ad close button left margin
        map.put(CustomExtraData.CLOSE_BTN_LEFT_MARGIN, -28);
        // Interstitial ad close button right margin
        map.put(CustomExtraData.CLOSE_BTN_RIGHT_MARGIN, 20);
        // Interstitial ad close button top margin
        map.put(CustomExtraData.CLOSE_BTN_TOP_MARGIN, 20);
        // Interstitial ad close button bottom margin
        map.put(CustomExtraData.CLOSE_BTN_BOTTOM_MARGIN, 0);
        // show close message on interstitial ad
        map.put(CustomExtraData.IS_ENDING_AD, true);
        // change Text on Close message
        map.put(CustomExtraData.ENDING_TEXT, "Press back to exit, thank you!");
        // change Text Size on Close message
        map.put(CustomExtraData.ENDING_TEXT_SIZE, 14);
        // change Text Color on Close message
        map.put(CustomExtraData.ENDING_TEXT_COLOR, Color.parseColor("#FFFFFF"));
        // change Text Background Gravity on Close message
        map.put(CustomExtraData.ENDING_TEXT_GRAVITY, Gravity.END);

        ad.setCustomExtras(map);
        */
        return ad;
    }

    // =============================================================================================
    // implements IOAMInterstitialEventListener
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
     * @param oamError
     */
    @Override
    public void onLoadFailed(OAMError oamError) {
        dismissProgressDialog();
        addMethodName(oamError.toString());
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
     * @param event
     */
    @Override
    public void onClosed(OAMCloseEvent event) {
        isOpened = false;
        addMethodName(event.name());
        showProgressDialog();
        // Reload interstitial ad
        interstitialAd.load();
    }

    /**
     * Called when the ad is clicked
     */
    @Override
    public void onClicked() {
        addMethodName(null);
    }
}
