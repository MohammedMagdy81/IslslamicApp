package com.example.myislamicapp.ui.fragments.tasbeeh;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myislamicapp.data.database.TasbeehDao;
import com.example.myislamicapp.data.database.TasbeehDatabase;
import com.example.myislamicapp.data.pojo.tasbeeh.Tasbeeh;

import java.util.ArrayList;
import java.util.List;

public class TasbeehViewModel extends AndroidViewModel {

    TasbeehDao dao;
    MutableLiveData<List<Tasbeeh>> allTasbeeh = new MutableLiveData<>();


    public TasbeehViewModel(@NonNull Application application) {
        super(application);
        dao = TasbeehDatabase.getInstance(getApplication().getApplicationContext()).tasbeehDao();
    }

    public void insertTasbeeh(Tasbeeh tasbeeh) {
        dao.insertTasbeeh(tasbeeh);
    }

    public void updateTasbeeh(Tasbeeh tasbeeh) {
        dao.updateSpecificTasbeeh(tasbeeh);
    }

    public void getAllTasbeeh() {
        allTasbeeh.setValue(dao.getAllTasbeeh());
    }

    public List<String> allTasbeeh() {
        ArrayList<String> getAllTasbeeh = new ArrayList<>();
        getAllTasbeeh.add("سبحان الله");
        getAllTasbeeh.add("الحمد لله");
        getAllTasbeeh.add("الله أكبر");
        getAllTasbeeh.add("لا حول ولاقوة إلا بالله");
        getAllTasbeeh.add("اللهم صلي علي محمد");
        getAllTasbeeh.add("سبحان الله وبحمده");
        getAllTasbeeh.add("أستغفر الله االعظيم");
        getAllTasbeeh.add("لاإله إلا الله");
        return getAllTasbeeh;
    }

}









