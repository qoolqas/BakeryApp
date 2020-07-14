package com.q.bakeryapp.ui.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.q.bakeryapp.R;

public class ManageActivity extends AppCompatActivity {
    CardView cardTambah, cardEdit, cardDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        cardTambah = findViewById(R.id.cardTambah);
        cardEdit = findViewById(R.id.cardEdit);
        cardDelete = findViewById(R.id.cardDelete);

        cardTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, CreateActivity.class);
                intent.putExtra("edit", "0");
                startActivity(intent);
            }
        });
        cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, EditRvActivity.class);
                startActivity(intent);
            }
        });
        cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
