package com.q.bakeryapp.ui.manage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mindorks.paracamera.Camera;
import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;

public class EditActivity extends AppCompatActivity {
    ProdukModel produkModel;
    private Camera cam;
    String edit = "0";
    String kat = "";
    Button simpan, galery, camera;
    EditText nama, rating, harga, deskripsi;
    ImageView photo;
    RadioGroup kategori;
    RadioButton basah, kering;
    Uri fileUri;
    public ProgressDialog pDialog;
    String domain = "192.168.1.5:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);
    }
}
