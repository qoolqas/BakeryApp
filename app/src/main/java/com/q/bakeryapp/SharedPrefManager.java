package com.q.bakeryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_NAME = "name";
    public static final String SP_EMAIL = "name";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveName(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public String getSpName() {
        return sp.getString(SP_NAME, "token");
    }
    public void saveEmail(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public String getSpEmail() {
        return sp.getString(SP_EMAIL, "email");
    }
    public  void clear(){
        spEditor.clear();
        spEditor.apply();
    }

}
