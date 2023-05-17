package com.example.myislamicapp.data.pojo.tafseer;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class TafseerResponseItem implements Parcelable {

	@SerializedName("author")
	private String author;

	@SerializedName("name")
	private String name;

	@SerializedName("language")
	private String language;

	@SerializedName("id")
	private int id;

	@SerializedName("book_name")
	private String bookName;

	protected TafseerResponseItem(Parcel in) {
		author = in.readString();
		name = in.readString();
		language = in.readString();
		id = in.readInt();
		bookName = in.readString();
	}

	public static final Creator<TafseerResponseItem> CREATOR = new Creator<TafseerResponseItem>() {
		@Override
		public TafseerResponseItem createFromParcel(Parcel in) {
			return new TafseerResponseItem(in);
		}

		@Override
		public TafseerResponseItem[] newArray(int size) {
			return new TafseerResponseItem[size];
		}
	};

	public String getAuthor(){
		return author;
	}

	public String getName(){
		return name;
	}

	public String getLanguage(){
		return language;
	}

	public int getId(){
		return id;
	}

	public String getBookName(){
		return bookName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeString(author);
		parcel.writeString(name);
		parcel.writeString(language);
		parcel.writeInt(id);
		parcel.writeString(bookName);
	}
}