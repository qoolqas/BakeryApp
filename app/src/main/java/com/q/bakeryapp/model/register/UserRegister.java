package com.q.bakeryapp.model.register;

import com.google.gson.annotations.SerializedName;

public class UserRegister {

	@SerializedName("nama")
	private String nama;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"nama = '" + nama + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}