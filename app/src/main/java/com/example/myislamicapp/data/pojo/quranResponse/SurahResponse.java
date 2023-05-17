package com.example.myislamicapp.data.pojo.quranResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurahResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Surah> data;

	@SerializedName("status")
	private String status;

	public int getCode(){
		return code;
	}

	public List<Surah> getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}
}