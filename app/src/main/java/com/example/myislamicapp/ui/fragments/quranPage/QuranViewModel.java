package com.example.myislamicapp.ui.fragments.quranPage;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.myislamicapp.data.PagesManager;

public class QuranViewModel {

    public Drawable getQuranImageByPageNumber(Context context, int pageNumber) {
        return PagesManager.getQuranImageByPageNum(context, pageNumber);
    }
}
