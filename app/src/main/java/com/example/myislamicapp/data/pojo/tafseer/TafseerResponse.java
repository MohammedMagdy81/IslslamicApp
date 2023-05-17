package com.example.myislamicapp.data.pojo.tafseer;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TafseerResponse{


	private List<TafseerResponseItem> tafseerResponse;

	public List<TafseerResponseItem> getTafseerResponse(){
		return tafseerResponse;
	}
}