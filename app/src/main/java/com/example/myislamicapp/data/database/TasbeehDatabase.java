package com.example.myislamicapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;

@Database(entities = {Tasbeeh.class}, version = 1, exportSchema = false)
public abstract class TasbeehDatabase extends RoomDatabase {

    private static TasbeehDatabase instance = null;

    public abstract TasbeehDao tasbeehDao();

    public static TasbeehDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (TasbeehDatabase.class) {
                if (instance == null) {
                    try {
                        String DATABASE_NAME = "tasbeeh.db";
                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                        TasbeehDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries()
                                .build();
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return instance;
    }
}
