package com.q.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.q.bakeryapp.model.ProdukModel;

public class DetailActivity extends AppCompatActivity {
    ProdukModel produkModel;
    TextView name,tipe,harga;

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

        name.setText(produkModel.getNama());
        tipe.setText(produkModel.getHarga());
    }
}
