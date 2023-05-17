package com.example.myislamicapp.ui.fragments.soralist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myislamicapp.data.utils.QuranFehresTabsUtils;
import com.example.myislamicapp.data.database.QuranDao;
import com.example.myislamicapp.data.database.QuranDatabase;
import com.example.myislamicapp.data.pojo.Jozz;
import com.example.myislamicapp.data.pojo.Sora;

import java.util.ArrayList;
import java.util.List;

public class SoraListViewModel extends AndroidViewModel {


    public SoraListViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<Sora> getAllSoras() {
        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
        ArrayList<Sora> soraArrayList = new ArrayList<>();
        for (int i = 1; i <= 114; i++) {
            soraArrayList.add(dao.getSoraByNumber(i));
        }
        return soraArrayList;
    }

    public List<?> provideTabsList(QuranFehresTabsUtils.QuranTabs currentTab) {

        switch (currentTab) {
            case SORA:
                return getAllSoras();
            case JOZZ:
                return getAllJozz();
            default:
                return null;
        }
    }

    private List<Jozz> getAllJozz() {
        QuranDao dao = QuranDatabase.getInstance(getApplication()).quranDao();
        ArrayList<Jozz> ajzaa = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            ajzaa.add(dao.getJozzByNumber(i));
        }
        return ajzaa;
    }
}
