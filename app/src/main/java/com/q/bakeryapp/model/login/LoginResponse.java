package com.q.bakeryapp.model.login;

import com.google.gson.annotations.SerializedName;
public class LoginResponse{

	@SerializedName("error")
	private boolean error;

	@SerializedName("user")
	private UserLogin user;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setUser(UserLogin user){
		this.user = user;
	}

	public UserLogin getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"error = '" + error + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}