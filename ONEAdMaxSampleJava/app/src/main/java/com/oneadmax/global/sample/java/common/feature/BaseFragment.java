package com.oneadmax.global.sample.java.common.feature;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {
    protected static String TAG = BaseFragment.class.getSimpleName();
    private CallbacksAdapter adapter;
    private final List<String> items = new ArrayList<>();

    protected T binding;

    @Nullable
    public static BaseFragment<?> newInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Log.d("BaseFragment", "newInstance >> " + className);
            TAG = clazz.getSimpleName();
            return (BaseFragment<?>) clazz.newInstance();
        } catch (Exception e) {
            Log.e("BaseFragment", "newInstance", e);
        }
        return null;
    }

    protected void initAdCallbacksView(@NonNull RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        adapter = new CallbacksAdapter(context, items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation()));
    }

    /**
     *  Add a message to the callback list.
     * @param message
     */
    protected void addMethodName(String message) {
        String methodName = new Throwable().getStackTrace()[1].getMethodName();
        if (message != null) {
            methodName += ": " + message;
        }

        Log.d(TAG, "addMethodName: " + methodName);
        if (adapter != null) {
            items.add(methodName);
            adapter.notifyItemInserted(items.size() - 1);
        }
    }

    protected void addAnonymousMethodName(String message) {
        String methodName = new Throwable().getStackTrace()[2].getMethodName();
        if (message != null) {
            methodName += ": " + message;
        }

        Log.d(TAG, "addAnonymousMethodName: " + methodName);
        if (adapter != null) {
            Log.d(TAG, "notifyItemInserted: " + methodName);
            items.add(methodName);
            adapter.notifyItemInserted(items.size() - 1);
        }
    }

    protected void setActionBarTitle(String title) {
        ((AdContentActivity) requireActivity()).setActionBarTitle(title);
    }

    protected void setActionBarSubTitle(String subTitle) {
        ((AdContentActivity) requireActivity()).setActionBarSubTitle(subTitle);
    }

    protected void showProgressDialog() {
        ((AdContentActivity) requireActivity()).showProgressDialog();
    }

    protected void dismissProgressDialog() {
        ((AdContentActivity) requireActivity()).dismissProgressDialog();
    }

    private static class CallbacksAdapter extends RecyclerView.Adapter<CallbacksViewHolder> {

        private final LayoutInflater inflater;
        private final List<String> items;

        CallbacksAdapter(final Context context, final List<String> items) {
            this.items = items;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public CallbacksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CallbacksViewHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CallbacksViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private static class CallbacksViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        CallbacksViewHolder(final View view) {
            super(view);
            textView = view.findViewById(android.R.id.text1);
            textView.setTextIsSelectable(true);
        }

        public void bind(String text) {
            textView.setText(text);
        }
    }
}
