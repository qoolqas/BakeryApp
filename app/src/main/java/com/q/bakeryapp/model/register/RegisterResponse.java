package com.q.bakeryapp.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("error")
	private boolean error;

	@SerializedName("user")
	private UserRegister user;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setUser(UserRegister user){
		this.user = user;
	}

	public UserRegister getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"error = '" + error + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}