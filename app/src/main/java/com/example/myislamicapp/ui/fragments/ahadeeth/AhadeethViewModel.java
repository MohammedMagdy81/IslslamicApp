package com.example.myislamicapp.ui.fragments.ahadeeth;

import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.ahadeethProvider.AhadeethListProvider;
import com.example.myislamicapp.data.ahadeethProvider.HadeethProvider;
import com.example.myislamicapp.data.pojo.ahadeeth.AhadeethList;
import com.example.myislamicapp.data.pojo.ahadeeth.Hadith;

import java.util.ArrayList;

public class AhadeethViewModel extends ViewModel {


    public ArrayList<AhadeethList> getAhadeethList() {
        return (ArrayList<AhadeethList>) AhadeethListProvider.getAhadeethList();
    }

    public ArrayList<Hadith> getAhadeethByIndex(int index) {
        return (ArrayList<Hadith>) HadeethProvider.getHadeeth(index);
    }

}
