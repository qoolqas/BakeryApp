package com.q.bakeryapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BakeryModel implements Parcelable {
    @SerializedName("gambar")
    private String nama;
    @SerializedName("gambar")
    private String path;
    @SerializedName("gambar")
    private String rating;
    @SerializedName("gambar")
    private String harga;
    @SerializedName("gambar")
    private String deskripsi;


    protected BakeryModel(Parcel in) {
        nama = in.readString();
        path = in.readString();
        rating = in.readString();
        harga = in.readString();
        deskripsi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(path);
        dest.writeString(rating);
        dest.writeString(harga);
        dest.writeString(deskripsi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakeryModel> CREATOR = new Creator<BakeryModel>() {
        @Override
        public BakeryModel createFromParcel(Parcel in) {
            return new BakeryModel(in);
        }

        @Override
        public BakeryModel[] newArray(int size) {
            return new BakeryModel[size];
        }
    };
}
