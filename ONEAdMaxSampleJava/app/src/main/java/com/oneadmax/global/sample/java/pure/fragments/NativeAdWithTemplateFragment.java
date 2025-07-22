package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMError;
import com.oneadmax.global.OAMNative;
import com.oneadmax.global.listener.IOAMNativeEventListener;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentNativeTemplateBinding;
import com.oneadmax.global.viewbinder.OAMViewBinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NativeAdWithTemplateFragment extends BaseFragment<FragmentNativeTemplateBinding> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNativeTemplateBinding.inflate(inflater, container, false);

        final Map<String, OAMNative> maps = new HashMap<>();
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_1, binding.OAMNative1);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_2, binding.OAMNative2);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_3, binding.OAMNative3);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_4, binding.OAMNative4);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_5, binding.OAMNative5);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_6, binding.OAMNative6);
        maps.put(PlacementIds.NATIVE_TEMPLATE_ID_7, binding.OAMNative7);

        // Set up the native ads
        for (Entry<String, OAMNative> entry : maps.entrySet()) {
            setupNativeAdWithTemplate(entry.getKey(), entry.getValue());
        }

        return binding.getRoot();
    }


    private void setupNativeAdWithTemplate(String placementId, OAMNative ad) {
        final OAMViewBinder viewBinder = new OAMViewBinder.Builder(-1)
                .useTemplate(true)
                .build();

        ad.setPlacementId(placementId)
                .setEventListener(new NativeEventListener(placementId))
                .setViewBinder(viewBinder)
                .load();

    }

    private static class NativeEventListener  implements IOAMNativeEventListener {
        private static final String TAG = "NativeEventListener";
        private final String placementId;
        public NativeEventListener(String placementId) {
            this.placementId = placementId;
        }

        /**
         * Called when the ad is loaded successfully.
         */
        @Override
        public void onLoaded() {
            Log.d(TAG, "onLoaded: " + placementId);
        }

        /**
         * Called when the ad fails to load.
         * @param error
         */
        @Override
        public void onLoadFailed(OAMError error) {
            Log.d(TAG, "onLoadFailed: " + placementId + " " + error.toString());
        }

        /**
         * Called when the ad is opened.
         */
        @Override
        public void onOpened() {
            Log.d(TAG, "onOpened: " + placementId);
        }

        /**
         * Called when the ad click
         */
        @Override
        public void onClicked() {
            Log.d(TAG, "onClicked: " + placementId);
        }

        /**
         * Called when the ad is Closed.
         */
        @Override
        public void onAdHidden() {Log.d(TAG, "onAdHidden");}
    }
}
