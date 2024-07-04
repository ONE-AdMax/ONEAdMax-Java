package com.oneadmax.global.sample.java.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oneadmax.global.sample.java.pure.BannerItem;

public final class Utils {

    public static String printBannerItem(@NonNull BannerItem item, @Nullable String error) {
        StringBuilder sb = new StringBuilder();
        sb.append(item.getSize().name()).append("(").append(item.getPlacementId()).append(")");

        if (!TextUtils.isEmpty(error)) {
            sb.append(": ").append(error);
        }

        return sb.toString();
    }

    public static String getSspVersion(@NonNull Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            if (bundle != null) {
                return bundle.getString("oneadmax:ssp_version");
            }
        } catch (Exception e) {
            Log.e("Utils", "Caught non-fatal exception while retrieving ", e);
        }
        return null;
    }
}
