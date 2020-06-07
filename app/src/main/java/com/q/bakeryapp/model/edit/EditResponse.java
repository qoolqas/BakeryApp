package com.q.bakeryapp.model.edit;

import com.google.gson.annotations.SerializedName;
public class EditResponse{

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
			"EditResponse{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}