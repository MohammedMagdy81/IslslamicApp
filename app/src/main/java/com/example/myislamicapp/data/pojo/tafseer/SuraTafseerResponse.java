package com.example.myislamicapp.data.pojo.tafseer;

import com.google.gson.annotations.SerializedName;

public class SuraTafseerResponse{

	@SerializedName("ayah_number")
	private int ayahNumber;

	@SerializedName("sura_index")
	private int suraIndex;

	@SerializedName("sura_name")
	private String suraName;

	@SerializedName("text")
	private String text;

	public int getAyahNumber(){
		return ayahNumber;
	}

	public int getSuraIndex(){
		return suraIndex;
	}

	public String getSuraName(){
		return suraName;
	}

	public String getText(){
		return text;
	}
}