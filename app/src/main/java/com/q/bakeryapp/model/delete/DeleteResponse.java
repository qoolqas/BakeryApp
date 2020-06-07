package com.q.bakeryapp.model.delete;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse{

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
			"DeleteResponse{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}