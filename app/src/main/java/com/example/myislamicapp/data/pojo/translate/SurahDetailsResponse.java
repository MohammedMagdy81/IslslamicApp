package com.example.myislamicapp.data.pojo.translate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurahDetailsResponse {

    @SerializedName("result")
    private List<SurahDetail> surahDetailList;

    public List<SurahDetail> getSurahDetailList() {
        return surahDetailList;
    }

    public void setSurahDetailList(List<SurahDetail> surahDetailList) {
        this.surahDetailList = surahDetailList;
    }
}
