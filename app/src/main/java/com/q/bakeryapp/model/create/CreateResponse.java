package com.q.bakeryapp.model.create;

import com.google.gson.annotations.SerializedName;

public class CreateResponse{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"CreateResponse{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}