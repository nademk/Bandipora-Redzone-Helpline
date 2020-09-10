package com.redzone.bandipora.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Categories implements Parcelable {
    String categoryName;

    public Categories() {
    }

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
    }

    protected Categories(Parcel in) {
        categoryName = in.readString();
    }

    public static Creator<Categories> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel source) {
            return new Categories(source);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}