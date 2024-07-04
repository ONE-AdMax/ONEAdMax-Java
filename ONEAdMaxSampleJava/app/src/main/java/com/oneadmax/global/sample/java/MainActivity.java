package com.oneadmax.global.sample.java;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;

import com.oneadmax.global.sample.java.common.Stopwatch;
import com.oneadmax.global.sample.java.common.Utils;
import com.oneadmax.global.sample.java.common.base.MenuActivity;
import com.oneadmax.global.sample.java.common.base.MenuItem;

import java.util.List;

public class MainActivity extends MenuActivity {

    private AlertDialog progressDialog;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBarSubTitle();
        showProgressDialog();
        binding.recyclerView.setVisibility(View.GONE);

        final Stopwatch stopwatch = Stopwatch.createStarted();
        viewModel.initialize(getApplicationContext(), () -> {
            dismissProgressDialog();
            stopwatch.stop();
            String message = "Initialization is complete.\n(" + stopwatch + " ms)";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            binding.recyclerView.setVisibility(View.VISIBLE);
        });
    }

    private void initActionBarSubTitle() {
        ActionBar actionBar = getSupportActionBar();
    }

    @Nullable
    @Override
    public List<MenuItem> onCreateMenuItems() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        }
        return viewModel.fetchItems();
    }

    @SuppressLint("SetTextI18n")
    private void showProgressDialog() {
        if (progressDialog == null) {
            View view = getLayoutInflater().inflate(R.layout.progress_dialog, null, false);
            ((TextView) view.findViewById(R.id.text)).setText("Initializing...");
            progressDialog = new AlertDialog.Builder(this)
                    .setView(view)
                    .setCancelable(false)
                    .create();
        }
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
