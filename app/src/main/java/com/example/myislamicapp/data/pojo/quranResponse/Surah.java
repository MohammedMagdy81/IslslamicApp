package com.example.myislamicapp.data.pojo.quranResponse;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class Surah implements Parcelable {

	@SerializedName("number")
	private int number;

	@SerializedName("englishName")
	private String englishName;

	@SerializedName("numberOfAyahs")
	private int numberOfAyahs;

	@SerializedName("revelationType")
	private String revelationType;

	@SerializedName("name")
	private String name;

	@SerializedName("englishNameTranslation")
	private String englishNameTranslation;

	public Surah(int number, String englishName, int numberOfAyahs, String revelationType, String name, String englishNameTranslation) {
		this.number = number;
		this.englishName = englishName;
		this.numberOfAyahs = numberOfAyahs;
		this.revelationType = revelationType;
		this.name = name;
		this.englishNameTranslation = englishNameTranslation;
	}

	public int getNumber(){
		return number;
	}

	public String getEnglishName(){
		return englishName;
	}

	public int getNumberOfAyahs(){
		return numberOfAyahs;
	}

	public String getRevelationType(){
		return revelationType;
	}

	public String getName(){
		return name;
	}

	public String getEnglishNameTranslation(){
		return englishNameTranslation;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {

	}
}