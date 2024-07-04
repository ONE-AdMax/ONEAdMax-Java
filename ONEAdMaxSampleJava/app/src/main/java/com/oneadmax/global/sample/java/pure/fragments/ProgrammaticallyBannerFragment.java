package com.oneadmax.global.sample.java.pure.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.OAMBanner;
import com.oneadmax.global.OAMError;
import com.oneadmax.global.listener.IOAMBannerEventListener;
import com.oneadmax.global.sample.java.R;
import com.oneadmax.global.sample.java.common.PlacementIds;
import com.oneadmax.global.sample.java.common.Utils;
import com.oneadmax.global.sample.java.common.feature.BaseFragment;
import com.oneadmax.global.sample.java.databinding.FragmentBannerProgrammaticallyBinding;
import com.oneadmax.global.sample.java.pure.BannerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammaticallyBannerFragment extends BaseFragment<FragmentBannerProgrammaticallyBinding> {

    private final List<OAMBanner> bannerList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBannerProgrammaticallyBinding.inflate(inflater, container, false);
        initAdCallbacksView(binding.recyclerView);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(false);

        setupOAMBanner(binding);
        return binding.getRoot();
    }

    private void setupOAMBanner(final FragmentBannerProgrammaticallyBinding binding) {
        // Set up the banner ads
        final Map<String, OAMBanner.Size> maps = new HashMap<String, OAMBanner.Size>() {{
            put(PlacementIds.BANNER_ID_320X50, OAMBanner.Size.BANNER_320x50);
            put(PlacementIds.BANNER_ID_320X100, OAMBanner.Size.BANNER_320x100);
            put(PlacementIds.BANNER_ID_300X250, OAMBanner.Size.BANNER_300x250);
        }};

        for (Map.Entry<String, OAMBanner.Size> entry : maps.entrySet()) {
            final OAMBanner banner = generateBannerAd(entry.getKey(), entry.getValue());
            banner.setLayoutParams(getLayoutParams());
            banner.load();

            // Add the title and banner to the container
            binding.container.addView(getTitleView(entry.getValue().name()));
            binding.container.addView(banner);
            bannerList.add(banner);
        }
    }

    private TextView getTitleView(String title) {
        final TextView titleView = new TextView(
                requireContext(),
                null,
                0,
                R.style.AppTextAppearance_Title_Background
        );
        titleView.setLayoutParams(getLayoutParams());
        titleView.setText(title);
        return titleView;
    }

    private ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    private OAMBanner generateBannerAd(final String placementId, final OAMBanner.Size size) {
        // Set the banner's placement ID, size, and other settings
        final OAMBanner banner = new OAMBanner(requireContext());
        banner.setId(View.generateViewId());
        banner.setPlacementId(placementId);
        banner.setSize(size);
        banner.setAnimType(OAMBanner.AnimType.SLIDE_LEFT);
        banner.setNetworkScheduleTimeout(10);
        banner.setRefreshTime(15);

        final BannerItem item = new BannerItem(banner, placementId, size);

        // Set the event listener for the banner
        banner.setEventListener(new IOAMBannerEventListener() {

            /**
             * Called when the banner ad is loaded successfully
             */
            @Override
            public void onLoaded() {
                addAnonymousMethodName(Utils.printBannerItem(item, null));
            }

            /**
             * Called when the banner ad fails to load
             * @param error
             */
            @Override
            public void onLoadFailed(OAMError error) {
                addAnonymousMethodName(Utils.printBannerItem(item, error.toString()));
            }

            /**
             * Called when the banner ad is clicked
             */
            @Override
            public void onClicked() {
                addAnonymousMethodName(Utils.printBannerItem(item, null));
            }
        });

        return banner;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Call the OamBanner's onResume method
        for (OAMBanner banner : bannerList) {
            banner.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Call the OamBanner's onPause method
        for (OAMBanner banner : bannerList) {
            banner.onPause();
        }
    }

}
