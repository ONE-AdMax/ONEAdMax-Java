package com.oneadmax.global.sample.java;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.oneadmax.global.ONEAdMax;
import com.oneadmax.global.listener.IOAMInitListener;
import com.oneadmax.global.sample.java.common.base.MenuItem;
import com.oneadmax.global.sample.java.main.MainContentsRepository;
import com.oneadmax.global.sample.java.main.ContentsRepositoryImpl;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainContentsRepository repository;

    public MainViewModel() {
        repository = new ContentsRepositoryImpl();
    }

    public void initialize(@NonNull final Context context, @Nullable final IOAMInitListener listener) {
        if (!ONEAdMax.isInit(context)) {
            ONEAdMax.setLogEnable(true);
            ONEAdMax.init(context, () -> {
                if (listener != null) {
                    listener.onInitialized();
                }
            });
        }
    }

    @Nullable
    public List<MenuItem> fetchItems() {
        return repository.fetchItems();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ONEAdMax.unInit();
    }
}
