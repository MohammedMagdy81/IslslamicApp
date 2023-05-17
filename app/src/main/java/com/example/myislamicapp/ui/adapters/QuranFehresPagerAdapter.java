package com.example.myislamicapp.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myislamicapp.data.utils.QuranFehresTabsUtils;
import com.example.myislamicapp.ui.fragments.soralist.SoraListFragment;

public class QuranFehresPagerAdapter extends FragmentStateAdapter {

    public static final int PAGE_COUNT = 2;

    public QuranFehresPagerAdapter(@NonNull Fragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SoraListFragment(QuranFehresTabsUtils.QuranTabs.SORA);
            case 1:
                return new SoraListFragment(QuranFehresTabsUtils.QuranTabs.JOZZ);
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
