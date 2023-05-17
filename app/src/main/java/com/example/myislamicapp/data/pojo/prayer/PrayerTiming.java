package com.example.myislamicapp.data.pojo.prayer;

public class PrayerTiming {
    private String prayerArabicName, prayerEnglishName, prayerTime;

    public PrayerTiming() {
    }

    public String getPrayerArabicName() {
        return prayerArabicName;
    }

    public String getPrayerEnglishName() {
        return prayerEnglishName;
    }

    public String getPrayerTime() {
        return prayerTime;
    }

    public PrayerTiming(String prayerArabicName, String prayerEnglishName, String prayerTime) {
        this.prayerArabicName = prayerArabicName;
        this.prayerEnglishName = prayerEnglishName;
        this.prayerTime = prayerTime;
    }
}
