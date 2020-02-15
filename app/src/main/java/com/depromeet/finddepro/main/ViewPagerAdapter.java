package com.depromeet.finddepro.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUMBER = 4;

    ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //@TODO (hee : fragments 구현 후 수정하기, 현재 테스트 용
        switch (position) {
            case 0: {
                return Fragments.getNoticesFragment();
            }
            case 1: {
                return Fragments.getScheduleFragment();
            }
            case 2: {
               return Fragments.getVotesFragment();
            }
//            case 3: {
//           return Fragments.getNoticesFragment();
//
//            }
            case 3:
            default: {
                return Fragments.getAttendanceFragment();

            }
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

}
