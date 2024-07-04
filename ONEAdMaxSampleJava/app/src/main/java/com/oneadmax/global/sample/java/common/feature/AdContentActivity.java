package com.oneadmax.global.sample.java.common.feature;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.oneadmax.global.sample.java.R;
import com.oneadmax.global.sample.java.common.Constants;
import com.oneadmax.global.sample.java.common.base.MenuItem;

public final class AdContentActivity extends AppCompatActivity {

    private ProgressDialogFragment progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

        MenuItem item = getIntent().getParcelableExtra(Constants.EXTRA_MENU_ITEM);
        if (item != null) {
            setActionBarTitle(item.getTitle());
            BaseFragment<?> fragment = BaseFragment.newInstance(item.getClassName());
            if (fragment != null) {
                fragment.setArguments(item.getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment, null)
                        .commit();
                return;
            }
        }

        finish();
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(title);
        }
    }

    public void setActionBarSubTitle(String subTitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setSubtitle(subTitle);
        }
    }

    public void showProgressDialog() {
        if (progress == null) {
            progress = new ProgressDialogFragment();
            progress.show(getSupportFragmentManager(), "progress");
        }
    }

    public void dismissProgressDialog() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }
}
