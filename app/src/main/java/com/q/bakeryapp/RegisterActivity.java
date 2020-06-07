package com.q.bakeryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.q.bakeryapp.connection.Client;
import com.q.bakeryapp.connection.Service;
import com.q.bakeryapp.model.login.LoginResponse;
import com.q.bakeryapp.model.register.RegisterResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout etNama, etAlamat, etEmail, etPassword;
    Button btnRegister;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPrefManager = new SharedPrefManager(this);
        initComponents();
    }

    private void initComponents() {
        etNama = findViewById(R.id.registerName);
        etAlamat = findViewById(R.id.loginEtAlamat);
        etEmail = findViewById(R.id.loginEtEmail);
        etPassword = findViewById(R.id.registerPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validatePassword() | !validateAlamat() | !validateEmail()) {

                    return;
                }
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();


            }
        });

    }
    private void register(){
        Client.getClient().create(Service.class).registerRequest(etNama.getEditText().getText().toString(),etEmail.getEditText().getText().toString(), etPassword.getEditText().getText().toString(),etAlamat.getEditText().getText().toString())
                .enqueue(new Callback<RegisterResponse>()
                {

                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            assert response.body() != null;
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(mContext);

                            dlgAlert.setMessage("Mohon Periksa Kembali");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();


                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

    private boolean validateName() {
        String email = (Objects.requireNonNull(etNama.getEditText())).getText().toString().trim();

        if (email.isEmpty()) {
            etNama.setError("Field Ini Tidak Boleh Kosong");
            return false;
        } else {
            etNama.setError(null);
            return true;
        }
    }

    private boolean validateAlamat() {
        String email = (Objects.requireNonNull(etAlamat.getEditText())).getText().toString().trim();

        if (email.isEmpty()) {
            etAlamat.setError("Field Ini Tidak Boleh Kosong");
            return false;
        } else {
            etAlamat.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = (Objects.requireNonNull(etEmail.getEditText())).getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Field Ini Tidak Boleh Kosong");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String pw = Objects.requireNonNull(etPassword.getEditText()).getText().toString().trim();

        if (pw.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }

}
