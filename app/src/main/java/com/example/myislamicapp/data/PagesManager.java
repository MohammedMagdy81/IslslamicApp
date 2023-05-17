package com.example.myislamicapp.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class PagesManager {

    public static Drawable getQuranImageByPageNum(Context context, int pageNumber) {
        DecimalFormat formatter = new DecimalFormat("000");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        formatter.setDecimalFormatSymbols(symbols);
        String drawableName = "quran/images/page" + formatter.format(pageNumber)+ ".png";
        InputStream istr = null;
        try {
            istr = context.getAssets().open(drawableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Drawable.createFromStream(istr, null);
    }

}
