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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public BakeryModel(String nama, String path, String rating, String harga, String deskripsi) {
        this.nama = nama;
        this.path = path;
        this.rating = rating;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }



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
