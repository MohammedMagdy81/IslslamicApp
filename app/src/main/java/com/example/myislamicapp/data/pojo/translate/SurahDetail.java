package com.example.myislamicapp.data.pojo.translate;

import com.google.gson.annotations.SerializedName;

public class SurahDetail {

    @SerializedName("id")
    private int id;

    @SerializedName("sura")
    private int sura;

    @SerializedName("aya")
    private int aya;

    @SerializedName("arabic_text")
    private String arabicText;

    @SerializedName("translation")
    private String translation;

    @SerializedName("footnotes")
    private String footnotes;

    public SurahDetail(int id, int sura, int aya, String arabicText, String translation, String footnotes) {
        this.id = id;
        this.sura = sura;
        this.aya = aya;
        this.arabicText = arabicText;
        this.translation = translation;
        this.footnotes = footnotes;
    }

    public int getId() {
        return id;
    }

    public int getSura() {
        return sura;
    }

    public int getAya() {
        return aya;
    }

    public String getArabicText() {
        return arabicText;
    }

    public String getTranslation() {
        return translation;
    }

    public String getFootnotes() {
        return footnotes;
    }
}
