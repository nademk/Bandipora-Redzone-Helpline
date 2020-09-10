package com.redzone.bandipora.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Posts implements Parcelable {
    String name;
    String profession;
    String phone;
    String category;

    public Posts() {
    }

    public Posts(String name, String profession, String phone, String category) {
        this.name = name;
        this.profession = profession;
        this.phone = phone;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public String getPhone() {
        return phone;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(profession);
        dest.writeString(phone);
        dest.writeString(category);
    }

    protected Posts(Parcel in) {
        name = in.readString();
        profession = in.readString();
        phone = in.readString();
        category = in.readString();
    }

    public static Creator<Posts> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel source) {
            return new Posts(source);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };
}