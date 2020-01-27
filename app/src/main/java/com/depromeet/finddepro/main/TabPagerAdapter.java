package com.depromeet.finddepro.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class TabPagerAdapter extends FragmentPagerAdapter {

    //@TODO (hee : fragments 구현 후 수정하기, 현재 테스트 용
    private static final int PAGE_NUMBER = 3;

    TabPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return Fragments.getNoticesFragment();
            }
            case 1: {
                return Fragments.getAttendanceInfoFragment();
            }
            case 2:
            default: {
                return Fragments.getScheduleFragment();

            }

        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "홈";
            case 1:
                return "출석";
            case 2:
                return "투표";
            case 3:
                return "게시판";
            default:
                return "Default";
        }
    }
}
