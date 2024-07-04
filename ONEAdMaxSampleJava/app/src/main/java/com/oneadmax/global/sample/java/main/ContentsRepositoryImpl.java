package com.oneadmax.global.sample.java.main;

import com.oneadmax.global.sample.java.common.base.MenuItem;
import com.oneadmax.global.sample.java.pure.BannerActivity;
import com.oneadmax.global.sample.java.pure.NativeAdActivity;
import com.oneadmax.global.sample.java.pure.fragments.InterstitialFragment;
import com.oneadmax.global.sample.java.pure.fragments.InterstitialVideoFragment;
import com.oneadmax.global.sample.java.pure.fragments.RewardVideoFragment;

import java.util.ArrayList;
import java.util.List;

public class ContentsRepositoryImpl implements MainContentsRepository {
    @Override
    public List<MenuItem> fetchItems() {
        return new ArrayList<MenuItem>() {{
            add(new MenuItem("Pure"));
            add(new MenuItem("Banner Ad", BannerActivity.class.getName()));
            add(new MenuItem("Interstitial Ad", InterstitialFragment.class.getName()));
            add(new MenuItem("Interstitial Video Ad", InterstitialVideoFragment.class.getName()));
            add(new MenuItem("Reward Video Ad", RewardVideoFragment.class.getName()));
            add(new MenuItem("Native Ad", NativeAdActivity.class.getName()));

        }};
    }
}
