package com.example.myislamicapp.ui.fragments.quransearch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.database.QuranDao;
import com.example.myislamicapp.data.database.QuranDatabase;
import com.example.myislamicapp.data.pojo.Aya;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private QuranDao quranDao;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        quranDao = QuranDatabase.getInstance(getApplication()).quranDao();
    }

    public List<Aya> getSearchAyat(String keySearch) {
       return quranDao.getAyaBySubText(keySearch);
    }
}
