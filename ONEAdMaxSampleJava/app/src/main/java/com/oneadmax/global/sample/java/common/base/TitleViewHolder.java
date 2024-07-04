package com.oneadmax.global.sample.java.common.base;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.oneadmax.global.sample.java.R;

public class TitleViewHolder extends RecyclerView.ViewHolder {
    protected final TextView titleView;

    public TitleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.title);
    }

    protected void bind(@Nullable MenuItem item) {
        if (item != null) {
            titleView.setText(item.getTitle());
        }
    }
}
