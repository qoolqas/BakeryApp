package com.q.bakeryapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProdukModel implements Parcelable {


    @SerializedName("nama")
    private String nama;

    @SerializedName("harga")
    private String harga;

    @SerializedName("rating")
    private String rating;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("produk_id")
    private String produkId;

    protected ProdukModel(Parcel in) {
        nama = in.readString();
        harga = in.readString();
        rating = in.readString();
        kategori = in.readString();
        deskripsi = in.readString();
        produkId = in.readString();
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getProdukId() {
        return produkId;
    }

    public void setProdukId(String produkId) {
        this.produkId = produkId;
    }

    public ProdukModel(String nama, String harga, String rating, String kategori, String deskripsi, String produkId) {
        this.nama = nama;
        this.harga = harga;
        this.rating = rating;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.produkId = produkId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(harga);
        parcel.writeString(rating);
        parcel.writeString(kategori);
        parcel.writeString(deskripsi);
        parcel.writeString(produkId);
    }
}
