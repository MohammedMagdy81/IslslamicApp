package com.example.myislamicapp.data.pojo.azkar;

import java.util.Objects;

public class ZekrType {

   private String zekrName;
   private int imageZekrId;

    public ZekrType(String zekrName, int imageZekrId) {
        this.zekrName = zekrName;
        this.imageZekrId = imageZekrId;
    }

    public ZekrType(String zekrName) {
        this.zekrName = zekrName;
    }

    public String getZekrName() {
        return zekrName;
    }

    public int getImageZekrId() {
        return imageZekrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZekrType zekrType = (ZekrType) o;
        return imageZekrId == zekrType.imageZekrId && Objects.equals(zekrName, zekrType.zekrName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zekrName, imageZekrId);
    }
}



