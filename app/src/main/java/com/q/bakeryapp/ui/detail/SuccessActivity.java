package com.q.bakeryapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.q.bakeryapp.Main2Activity;
import com.q.bakeryapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SuccessActivity extends AppCompatActivity {
    TextView sukses, kembali, kode;
    SimpleDateFormat sdfc;
    String myFormats = "dd/MM/yyyy hh:mm:ss";
    Date currentTime;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        sukses = findViewById(R.id.textView7);
        kembali = findViewById(R.id.kembali);
        sdfc = new SimpleDateFormat(myFormats, Locale.US);
        currentTime = Calendar.getInstance().getTime();
        kode = findViewById(R.id.kode);
        kode.setText("Kode Pembelian : "+sdfc.format(currentTime).replaceAll("/", "").replace(" ", "").replaceAll(":", ""));
        sukses.setText("Sukses");
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, Main2Activity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
