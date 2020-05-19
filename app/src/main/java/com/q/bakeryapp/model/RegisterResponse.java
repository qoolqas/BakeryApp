package com.q.bakeryapp.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("error")
	private boolean error;

	@SerializedName("user")
	private User user;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
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