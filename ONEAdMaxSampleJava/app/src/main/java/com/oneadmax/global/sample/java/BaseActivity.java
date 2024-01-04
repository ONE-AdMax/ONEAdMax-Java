package com.oneadmax.global.sample.java;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected static final String TAG = "ONEAdMaxSampleJava";
    private ProgressBar progressBar = null;

    //----------------------------------------------------------------------------------------------

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        this.progressBar = view.findViewById(R.id.progressBar);
    }

    protected void showActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void showToast(String str) {
        Log.d(TAG, str);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void showProgressBar() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgressBar() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}