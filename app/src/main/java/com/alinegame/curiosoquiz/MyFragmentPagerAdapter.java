package com.alinegame.curiosoquiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Aline Silva Gonzaga on 26/02/2018.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[] { "", "", "" };

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();

            case 1:
                return new ResumoFragment();

            case 2:
                return new BonusFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
