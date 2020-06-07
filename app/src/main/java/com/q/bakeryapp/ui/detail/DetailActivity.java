package com.q.bakeryapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;

public class DetailActivity extends AppCompatActivity {
    ProdukModel produkModel;
    TextView name,tipe,harga;
    Button beli;

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
        beli = findViewById(R.id.detail_beli);

        name.setText(produkModel.getNama());
        tipe.setText(produkModel.getHarga());
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
