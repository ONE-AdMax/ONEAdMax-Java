package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMNative;
import com.oneadmax.global.listener.IOAMNativeEventListener;
import com.oneadmax.global.sample.java.R;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentNativeLayoutBinding;
import com.oneadmax.global.viewbinder.OAMViewBinder;

// if you want to use the Layout Editor, you need to add the following import statement
public class LayoutEditorNativeAdFragment extends BaseFragment<FragmentNativeLayoutBinding> implements IOAMNativeEventListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNativeLayoutBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        showProgressDialog();

        setupNativeAd(binding.OAMNative);
        return binding.getRoot();
    }

    private void setupNativeAd(OAMNative oamNative) {
        oamNative.setPlacementId(PlacementIds.NATIVE_ID);
        oamNative.setEventListener(this);
        // Set up Layout Editor
        OAMViewBinder viewBinder = new OAMViewBinder.Builder(R.id.include_oneadmax_native_ad)
                .iconImageViewId(R.id.icon_image_view)
                .titleViewId(R.id.title_text_view)
                .descViewId(R.id.desc_text_view)
                .callToActionViewId(R.id.cta_text_view)
                .mainImageViewId(R.id.main_image_view)
                .build();
        oamNative.setViewBinder(viewBinder);
        oamNative.load();
    }


    // =============================================================================================
    // implements IOAMNativeEventListener
    // =============================================================================================

    /**
     * Called when the ad is loaded
     */
    @Override
    public void onLoaded() {
        dismissProgressDialog();
        addMethodName(null);
    }

    /**
     * Called when the ad is failed to load
     * @param error
     */
    @Override
    public void onLoadFailed(OAMError error) {
        dismissProgressDialog();
        addMethodName(error.toString());
    }

    /**
     * Called When the ad is opened
     */
    @Override
    public void onOpened() {
        addMethodName(null);
    }

    /**
     *  Called when the ad is clicked
     */
    @Override
    public void onClicked() {
        addMethodName(null);
    }
}
