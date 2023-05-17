package com.example.myislamicapp.data.pojo.tafseer;

import com.google.gson.annotations.SerializedName;

public class Tafseer{

	@SerializedName("tafseer_id")
	private int tafseerId;

	@SerializedName("ayah_number")
	private int ayahNumber;

	@SerializedName("tafseer_name")
	private String tafseerName;

	@SerializedName("text")
	private String text;

	@SerializedName("ayah_url")
	private String ayahUrl;

	public int getTafseerId(){
		return tafseerId;
	}

	public int getAyahNumber(){
		return ayahNumber;
	}

	public String getTafseerName(){
		return tafseerName;
	}

	public String getText(){
		return text;
	}

	public String getAyahUrl(){
		return ayahUrl;
	}
}