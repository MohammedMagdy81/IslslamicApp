package com.example.myislamicapp.data.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;

import java.util.List;

@Dao
public interface TasbeehDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasbeeh(Tasbeeh tasbeeh);

    @Update
    void updateSpecificTasbeeh(Tasbeeh tasbeeh);

    @Query("SELECT*FROM Tasbeeh")
    List<Tasbeeh> getAllTasbeeh();
}
