package com.example.myislamicapp.data.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.myislamicapp.data.pojo.Aya;
import com.example.myislamicapp.data.pojo.Jozz;
import com.example.myislamicapp.data.pojo.Sora;

import java.util.List;

@Dao
public interface QuranDao {

    @Query("SELECT * FROM quran WHERE page = :page")
    List<Aya> getAyatByPage(int page);

    @Query("SELECT sora as soraNumber, MIN(page) as startPage ,MAX(page) as endPage ,sora_name_ar as arabicName,sora_name_en as englishName FROM quran WHERE sora = :soraNumber")
    Sora getSoraByNumber(int soraNumber);

    @Query("SELECT jozz as jozzNumber, MIN(page) as startPage ,MAX(page) as endPage FROM quran WHERE jozz = :jozzNumber")
    Jozz getJozzByNumber(int jozzNumber);

    @Query("SELECT * FROM quran where aya_text_emlaey LIKE '%' || :keySearch ||'%'")
    List<Aya> getAyaBySubText(String keySearch);
}

