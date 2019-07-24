package com.example.chatrealtimetracking.fragment.report.model;

import com.google.gson.annotations.SerializedName;

public class ResponsePengaduan{

	@SerializedName("msg")
	private String msg;

	@SerializedName("isSuccess")
	private boolean isSuccess;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	public boolean isIsSuccess(){
		return isSuccess;
	}
}