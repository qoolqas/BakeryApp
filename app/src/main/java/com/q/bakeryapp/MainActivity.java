package com.q.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.q.bakeryapp.ui.ProdukActivity;

public class MainActivity extends AppCompatActivity {
    TextView name;
    SharedPrefManager sharedPrefManager;
    CardView cardRekomendasi;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);
        name = findViewById(R.id.name);
        name.setText("Welcome " + sharedPrefManager.getSpName());

        cardRekomendasi = findViewById(R.id.cardRekomendasi);
        cardRekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProdukActivity.class);
                startActivity(intent);
            }
        });
    }
}
