package com.depromeet.finddepro.main.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.depromeet.finddepro.main.Fragments;

public class HomeFragmentTabAdapter extends FragmentPagerAdapter {

    private int mNumOfTabs; //탭의 갯수

    HomeFragmentTabAdapter(FragmentManager fm, int numTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragments.getNoticesFragment();
            case 1:
            default:
                return Fragments.getScheduleFragment();

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}