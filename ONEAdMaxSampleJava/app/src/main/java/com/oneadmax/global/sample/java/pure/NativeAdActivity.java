package com.oneadmax.global.sample.java.pure;

import com.oneadmax.global.sample.java.common.base.MenuActivity;
import com.oneadmax.global.sample.java.common.base.MenuItem;
import com.oneadmax.global.sample.java.pure.fragments.LayoutEditorNativeAdFragment;
import com.oneadmax.global.sample.java.pure.fragments.NativeAdWithTemplateFragment;

import java.util.ArrayList;
import java.util.List;

public class NativeAdActivity extends MenuActivity {
    @Override
    public List<MenuItem> onCreateMenuItems() {
        return new ArrayList<MenuItem>() {{
            add(new MenuItem("Layout Editor Native Ad", LayoutEditorNativeAdFragment.class.getName()));
            add(new MenuItem("Template With Native Ad", NativeAdWithTemplateFragment.class.getName()));
        }};
    }
}
