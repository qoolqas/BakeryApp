package com.q.bakeryapp.ui.manage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mindorks.paracamera.Camera;
import com.q.bakeryapp.R;
import com.q.bakeryapp.connection.Client;
import com.q.bakeryapp.connection.Service;
import com.q.bakeryapp.model.create.CreateResponse;
import com.q.bakeryapp.model.produk.ProdukModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        produkModel = getIntent().getParcelableExtra("data");
        nama = findViewById(R.id.rotiNama);
        rating = findViewById(R.id.rotiRating);
        harga = findViewById(R.id.rotiHarga);
        deskripsi = findViewById(R.id.rotiDeskripsi);
        photo = findViewById(R.id.iv_Photo);
        camera = findViewById(R.id.bt_camera);
        galery = findViewById(R.id.bt_gallery);
        simpan = findViewById(R.id.bt_simpan);

        kategori = findViewById(R.id.rd_kategori);
        basah = findViewById(R.id.rd_basah);
        kering = findViewById(R.id.rd_kering);
        try {
            edit = getIntent().getStringExtra("edit");

        } catch (Exception e) {
            edit = "0";
        }
        kategori.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_basah:
                        kat = "basah";
                        break;
                    case R.id.rd_kering:
                        kat = "kering";
                        break;
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam = new Camera.Builder()
                        .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                        .setTakePhotoRequestCode(1)
                        .setDirectory("pics")
                        .setName("gemar" + System.currentTimeMillis())
                        .setImageFormat(Camera.IMAGE_JPEG)
                        .setCompression(50)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                        .build(CreateActivity.this);

                try {
                    cam.takePicture();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);//one can be replaced with any action code
            }
        });
        if (edit.equals("1")) {
            simpan.setText("Simpan Perubahan");
            setEdit();
        }
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.equals("1")) {
                    editRoti();
                } else {
                    save();
                }

            }
        });
        initDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .override(300, 300);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = cam.getCameraBitmap();
                    if (bitmap != null) {
                        Glide.with(CreateActivity.this)
                                .asBitmap()
                                .apply(myOptions)
                                .load(bitmap)
                                .into(photo);
                    } else {
                        Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    Glide.with(CreateActivity.this)
                            .asBitmap()
                            .apply(myOptions)
                            .load(selectedImage)
                            .into(photo);
                }
                break;
        }
    }

    private void saveMedia(Bitmap photo) {
        File myDirPhotos = new File(Environment.getExternalStorageDirectory() + File.separator + "BakkeryApp/Temp");
        myDirPhotos.mkdirs();
        String photoname = nama.getText().toString() + "photo" + "_" + "_.png";
        File file = new File(myDirPhotos, photoname);
        String pathPhoto = file.toString();
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            photo.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        fileUri = Uri.parse(pathPhoto);

    }

    private void save() {
        BitmapDrawable fotoD = (BitmapDrawable) photo.getDrawable();
        Bitmap foto = fotoD.getBitmap();
        saveMedia(foto);
        upload();
    }

    private void upload() {
        try {
            showpDialog();
            Map<String, RequestBody> map = new HashMap<>();
            File foto = new File(String.valueOf(fileUri));
            // Parsing any Media type file
            String sNama = Objects.requireNonNull(nama.getText().toString().trim());
            String sRating = Objects.requireNonNull(rating.getText().toString().trim());
            String sHarga = Objects.requireNonNull(harga.getText().toString().trim());
            String sKategori = Objects.requireNonNull(kat);
            String sDeskripsi = Objects.requireNonNull(deskripsi.getText().toString().trim());


            RequestBody reqNama = RequestBody.create(sNama, MediaType.parse("multipart/form-data"));
            RequestBody reqRating = RequestBody.create(sRating, MediaType.parse("multipart/form-data"));
            RequestBody reqHarga = RequestBody.create(sHarga, MediaType.parse("multipart/form-data"));
            RequestBody reqKategori = RequestBody.create(sKategori, MediaType.parse("multipart/form-data"));
            RequestBody reqDeskripsi = RequestBody.create(sDeskripsi, MediaType.parse("multipart/form-data"));

            RequestBody requestBody = RequestBody.create(MediaType.parse(" "), foto);
            map.put("foto\"; filename=\"" + foto.getName() + "\"", requestBody);
            Service service = Client.getClient().create(Service.class);
            Call<CreateResponse> call = service.create( reqNama, reqRating, reqHarga, reqKategori, reqDeskripsi, map);
            call.enqueue(new Callback<CreateResponse>() {
                @Override
                public void onResponse(@NotNull Call<CreateResponse> call, @NotNull Response<CreateResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            hidepDialog();
                            Toast.makeText(getApplicationContext(), getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    } else {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<CreateResponse> call, @NotNull Throwable t) {
                    hidepDialog();
                    Log.v("Response gotten is", Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal) + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Silahkan Cek Kembali", Toast.LENGTH_SHORT).show();
            Log.d("catch", "catch");
        }


    }
    private void setEdit(){
        nama.setText(produkModel.getNama());
        rating.setText(produkModel.getRating());
        harga.setText(produkModel.getHarga());
        if (produkModel.getKategori().equals("basah")) {
            kategori.check(R.id.rd_basah);
        } else {
            kategori.check(R.id.rd_kering);
        }
        deskripsi.setText(produkModel.getDeskripsi());
    }
    private void editRoti(){
        try {
            showpDialog();
            Map<String, RequestBody> map = new HashMap<>();
            File foto = new File(String.valueOf(fileUri));
            // Parsing any Media type file
            String sNama = Objects.requireNonNull(nama.getText().toString().trim());
            String sRating = Objects.requireNonNull(rating.getText().toString().trim());
            String sHarga = Objects.requireNonNull(harga.getText().toString().trim());
            String sKategori = Objects.requireNonNull(kat);
            String sDeskripsi = Objects.requireNonNull(deskripsi.getText().toString().trim());


            RequestBody reqNama = RequestBody.create(sNama, MediaType.parse("multipart/form-data"));
            RequestBody reqRating = RequestBody.create(sRating, MediaType.parse("multipart/form-data"));
            RequestBody reqHarga = RequestBody.create(sHarga, MediaType.parse("multipart/form-data"));
            RequestBody reqKategori = RequestBody.create(sKategori, MediaType.parse("multipart/form-data"));
            RequestBody reqDeskripsi = RequestBody.create(sDeskripsi, MediaType.parse("multipart/form-data"));

            RequestBody requestBody = RequestBody.create(MediaType.parse(" "), foto);
            map.put("foto\"; filename=\"" + foto.getName() + "\"", requestBody);
            Service service = Client.getClient().create(Service.class);
            Call<CreateResponse> call = service.create( reqNama, reqRating, reqHarga, reqKategori, reqDeskripsi, map);
            call.enqueue(new Callback<CreateResponse>() {
                @Override
                public void onResponse(@NotNull Call<CreateResponse> call, @NotNull Response<CreateResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            hidepDialog();
                            Toast.makeText(getApplicationContext(), getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    } else {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<CreateResponse> call, @NotNull Throwable t) {
                    hidepDialog();
                    Log.v("Response gotten is", Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_gagal) + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Silahkan Cek Kembali", Toast.LENGTH_SHORT).show();
            Log.d("catch", "catch");
        }
    }
    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(false);
    }


    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }
}
