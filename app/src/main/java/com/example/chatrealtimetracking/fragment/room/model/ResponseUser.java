package com.example.chatrealtimetracking.fragment.room.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUser{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("isSuccess")
	private boolean isSuccess;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	public boolean isIsSuccess(){
		return isSuccess;
	}
}