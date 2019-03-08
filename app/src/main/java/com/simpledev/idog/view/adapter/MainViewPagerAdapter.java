package com.simpledev.idog.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFrag = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mListFrag.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mListFrag.get(position);
    }

    public void setFragment(Fragment fragment, String title) {
        mListFrag.add(fragment);
        mListTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
