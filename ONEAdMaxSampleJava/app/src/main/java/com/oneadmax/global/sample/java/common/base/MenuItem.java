package com.oneadmax.global.sample.java.common.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MenuItem implements Parcelable {

    private final String title;
    private final String description;
    private final String className;
    private final Bundle extras;

    public MenuItem(@NonNull String title) {
        this(title, null, null, null);
    }
    public MenuItem(@NonNull String title, @Nullable String className) {
        this(title, className, null, null);
    }
    public MenuItem(@NonNull String title, @Nullable String className, @Nullable String description) {
        this(title, className, description, null);
    }
    public MenuItem(@NonNull String title, @Nullable String className, @Nullable Bundle extras) {
        this(title, className, null, extras);
    }
    public MenuItem(@NonNull String title, @Nullable String className, @Nullable String description, @Nullable Bundle extras) {
        this.title = title;
        this.className = className;
        this.description = description;
        this.extras = extras;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getClassName() {
        return className;
    }
    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public Bundle getExtras() {
        return extras;
    }

    protected MenuItem(Parcel in) {
        title = in.readString();
        className = in.readString();
        description = in.readString();
        extras = in.readBundle(getClass().getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(className);
        dest.writeString(description);
        dest.writeBundle(extras);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };


}
