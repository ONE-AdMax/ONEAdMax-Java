package com.oneadmax.global.sample.java.common.base;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.oneadmax.global.sample.java.R;

import java.util.List;

/**
 * Main Screen Adapter
 */
public final class MenuAdapter extends RecyclerView.Adapter<TitleViewHolder> {

    public static final int VIEW_TYPE_TITLE = 0;
    public static final int VIEW_TYPE_CONTENT = 1;

    private final List<MenuItem> items;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public MenuAdapter(@NonNull List<MenuItem> items, @NonNull OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        MenuItem item = getItem(position);
        if (item == null || TextUtils.isEmpty(item.getClassName())) {
            return VIEW_TYPE_TITLE;
        }
        return VIEW_TYPE_CONTENT;
    }

    @NonNull
    @Override
    public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_CONTENT) {
            return new ContentViewHolder(inflater.inflate(R.layout.item_content_row, parent, false), listener);
        }
        return new TitleViewHolder(inflater.inflate(R.layout.item_title_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Nullable
    private MenuItem getItem(int position) {
        if (getItemCount() > position) {
            return items.get(position);
        } else {
            return null;
        }
    }
}
