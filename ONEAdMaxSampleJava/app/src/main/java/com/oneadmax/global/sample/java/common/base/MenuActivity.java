package com.oneadmax.global.sample.java.common.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oneadmax.global.sample.java.common.Constants;
import com.oneadmax.global.sample.java.common.feature.AdContentActivity;
import com.oneadmax.global.sample.java.databinding.ActivityRecyclerBinding;

import java.util.List;
import java.util.Objects;

public abstract class MenuActivity extends AppCompatActivity {

    protected ActivityRecyclerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MenuItem item = (MenuItem) getIntent().getParcelableExtra(Constants.EXTRA_MENU_ITEM);
        if (item != null) {
            setActionBarTitle(item.getTitle());
        }

        setupRecyclerView(binding.recyclerView);
    }

    protected void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Nullable
    public abstract List<MenuItem> onCreateMenuItems();

    private void setupRecyclerView(final RecyclerView recyclerView) {
        final List<MenuItem> items = onCreateMenuItems();

        if (items == null || items.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            return;
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new PaddingItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(items, this::itemClick));
    }

    private void itemClick(@NonNull final MenuItem item) {
        final String className = item.getClassName();
        try {
            Objects.requireNonNull(className);
            Class<?> clazz = Class.forName(className);
            Intent intent = null;
            if (Activity.class.isAssignableFrom(clazz)) {
                intent = new Intent(this, clazz);
            } else if (Fragment.class.isAssignableFrom(clazz)) {
                intent = new Intent(this, AdContentActivity.class);
            }

            if (intent != null) {
                intent.putExtra(Constants.EXTRA_MENU_ITEM, item);
                startActivity(intent);
            } else {
                throw new ClassNotFoundException();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(
                    getApplicationContext(),
                    "Activity or Fragment class not found.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private static class PaddingItemDecoration extends RecyclerView.ItemDecoration {
        private final int padding;
        public PaddingItemDecoration() {
            padding = (int)(10 * Resources.getSystem().getDisplayMetrics().density);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

            int adapterPosition = parent.getChildAdapterPosition(view);
            int itemCount = parent.getAdapter() != null ? parent.getAdapter().getItemCount() - 1 : 0;

            outRect.left = padding;
            outRect.right = padding;

            if (adapterPosition == itemCount)
                outRect.bottom = padding;

        }
    }
}
