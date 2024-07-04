package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMBanner;
import com.oneadmax.global.OAMError;
import com.oneadmax.global.listener.IOAMBannerEventListener;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.Utils;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentBannerLayoutBinding;
import com.oneadmax.global.sample.java.pure.BannerItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutEditorBannerFragment extends BaseFragment<FragmentBannerLayoutBinding> {

    private final List<OAMBanner> bannerList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBannerLayoutBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (BannerItem item : getBannerItems()) {
            final OAMBanner banner = item.getBanner();
            onBindBannerView(item);
            banner.load();
            bannerList.add(banner);
        }
    }

    public List<BannerItem> getBannerItems() {
        return new ArrayList<BannerItem>() {{
            add(new BannerItem(binding.banner320x50, PlacementIds.BANNER_ID_320X50, OAMBanner.Size.BANNER_320x50));
            add(new BannerItem(binding.banner320x100, PlacementIds.BANNER_ID_320X100, OAMBanner.Size.BANNER_320x100));
            add(new BannerItem(binding.banner300x250, PlacementIds.BANNER_ID_300X250, OAMBanner.Size.BANNER_300x250));
        }};
    }


    public void onBindBannerView(final BannerItem item) {
        // Set the banner's placement ID, size, and other settings
        final OAMBanner banner = item.getBanner();

        banner.setPlacementId(item.getPlacementId());
        banner.setSize(item.getSize());
        banner.setAnimType(OAMBanner.AnimType.SLIDE_LEFT);
        banner.setNetworkScheduleTimeout(10);
        banner.setRefreshTime(15);

        banner.setEventListener(new IOAMBannerEventListener() {
            @Override
            public void onLoaded() {
                addAnonymousMethodName(Utils.printBannerItem(item, null));
            }

            @Override
            public void onLoadFailed(OAMError error) {
                addAnonymousMethodName(Utils.printBannerItem(item, error.toString()));
            }

            @Override
            public void onClicked() {
                addAnonymousMethodName(Utils.printBannerItem(item, null));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // It is essential to prevent memory wastage.
        // Call the OamBanner's onResume method
        for (OAMBanner banner : bannerList) {
            banner.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // It is essential to prevent memory wastage.
        // Call the OamBanner's onPause method
        for (OAMBanner banner : bannerList) {
            banner.onPause();
        }
    }
}
