package com.oneadmax.global.sample.java.common.base;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.sample.java.R;

public class ContentViewHolder extends TitleViewHolder {

    protected final TextView descView;
    private final MenuAdapter.OnItemClickListener listener;

    public ContentViewHolder(@NonNull View itemView, @NonNull MenuAdapter.OnItemClickListener listener) {
        super(itemView);
        descView = itemView.findViewById(R.id.desc);
        this.listener = listener;
    }

    @Override
    protected void bind(@Nullable MenuItem item) {
        super.bind(item);
        if (item != null) {
            if (TextUtils.isEmpty(item.getDescription())) {
                descView.setVisibility(View.GONE);
            } else {
                descView.setVisibility(View.VISIBLE);
                descView.setText(item.getDescription());
            }

            itemView.setOnClickListener(v -> {
                listener.onItemClick(item);
            });
        }
    }
}
