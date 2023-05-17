package com.example.myislamicapp.ui.fragments.fehres;

import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.utils.QuranFehresTabsUtils;

public class QuranFehresViewModel extends ViewModel {

    private String[] tabsList;

    public QuranFehresViewModel() {
        tabsList = QuranFehresTabsUtils.QURAN_INDEX_TABS;
    }

    public String getTabAt(int position) {
        return tabsList[position];
    }

}
