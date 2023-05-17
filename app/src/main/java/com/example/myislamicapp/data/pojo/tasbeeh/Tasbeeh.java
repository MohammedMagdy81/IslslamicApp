package com.example.myislamicapp.data.pojo.tasbeeh;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tasbeeh {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Tasbeeh(String name, int count, String timeStamp) {
        this.name = name;
        this.count = count;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int count;
    private String timeStamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
