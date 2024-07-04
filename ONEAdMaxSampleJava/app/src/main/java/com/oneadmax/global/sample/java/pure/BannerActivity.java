package com.oneadmax.global.sample.java.pure;

import com.oneadmax.global.sample.java.common.base.MenuActivity;
import com.oneadmax.global.sample.java.common.base.MenuItem;
import com.oneadmax.global.sample.java.pure.fragments.LayoutEditorBannerFragment;
import com.oneadmax.global.sample.java.pure.fragments.ProgrammaticallyBannerFragment;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends MenuActivity {
    @Override
    public List<MenuItem> onCreateMenuItems() {
        return new ArrayList<MenuItem>() {{
            add(new MenuItem("Layout Editor Banner Ad", LayoutEditorBannerFragment.class.getName()));
            add(new MenuItem("Programmatically Banner Ad", ProgrammaticallyBannerFragment.class.getName()));
        }};
    }
}
