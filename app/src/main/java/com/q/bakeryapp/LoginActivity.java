package com.q.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout etNama, etPassword;
    Button btnLogin;
    Context mContext = this;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(this);
        initComponents();

    }
    private void initComponents(){
        etNama = findViewById(R.id.loginEtName);
        etPassword =  findViewById(R.id.loginEtPassword);
        btnLogin = findViewById(R.id.loginButtonLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validatePassword()) {
                    return;
                }
                sharedPrefManager.saveName(SharedPrefManager.SP_NAME, Objects.requireNonNull(etNama.getEditText()).getText().toString().trim());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        });

    }
    private boolean validateName() {
        String email = (Objects.requireNonNull(etNama.getEditText())).getText().toString().trim();

        if (email.isEmpty()) {
            etNama.setError("Email tidak boleh kosong");
            return false;
        }  else {
            etNama.setError(null);
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
