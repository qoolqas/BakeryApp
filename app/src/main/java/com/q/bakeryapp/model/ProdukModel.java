package com.q.bakeryapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProdukModel implements Parcelable {

    @SerializedName("produk_id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("gambar")
    private String path;
    @SerializedName("rating")
    private String rating;
    @SerializedName("harga")
    private int harga;
    @SerializedName("deskripsi")
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ProdukModel(String nama, String path, String rating, int harga, String deskripsi) {
        this.nama = nama;
        this.path = path;
        this.rating = rating;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }



    protected ProdukModel(Parcel in) {
        nama = in.readString();
        path = in.readString();
        rating = in.readString();
        harga = in.readInt();
        deskripsi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(path);
        dest.writeString(rating);
        dest.writeInt(harga);
        dest.writeString(deskripsi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProdukModel> CREATOR = new Creator<ProdukModel>() {
        @Override
        public ProdukModel createFromParcel(Parcel in) {
            return new ProdukModel(in);
        }

        @Override
        public ProdukModel[] newArray(int size) {
            return new ProdukModel[size];
        }
    };
}
