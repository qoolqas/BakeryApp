package com.q.bakeryapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;

public class DetailActivity extends AppCompatActivity {
    ProdukModel produkModel;
    TextView name, tipe, harga, deskripsi;
    Button beli;
    ImageView image;
    RatingBar rating;
    String ip = "192.168.1.9:8080";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        produkModel = getIntent().getParcelableExtra("data");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(produkModel.getNama());

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white_background));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.transparent));

        name = findViewById(R.id.detail_title);
        tipe = findViewById(R.id.detail_kategori);
        harga = findViewById(R.id.detail_harga);
        beli = findViewById(R.id.detail_beli);
        image = findViewById(R.id.detail_image);
        deskripsi = findViewById(R.id.detail_deskripsi);
        rating = findViewById(R.id.detail_rating_star);

        Glide.with(getApplicationContext()).load("http://"+ ip +"/roti/file/"+produkModel.getFoto()).into(image);
        name.setText(produkModel.getNama());
        rating.setRating(Float.parseFloat(produkModel.getRating()) / 2);
        tipe.setText("Kue " + produkModel.getKategori());
        harga.setText("Rp " + produkModel.getHarga());
        deskripsi.setText(produkModel.getDeskripsi());
        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, PembayaranActivity.class);
                intent.putExtra("data", produkModel);
                startActivity(intent);
            }
        });


    }
}
