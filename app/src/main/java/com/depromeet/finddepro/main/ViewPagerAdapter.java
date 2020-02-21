package com.depromeet.finddepro.main;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.depromeet.finddepro.main.home.HomeFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUMBER = 5;

    ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //@TODO (hee : fragments 구현 후 수정하기, 현재 테스트 용)
        switch (position) {
            case 0: {
                //공지사항, 일정 합치기
                return new HomeFragment();
            }
            case 1: {
                //출석체
                return Fragments.getAttendanceFragment();
            }
            case 2: {
                //투표
                return Fragments.getVotesFragment();
            }
            case 3: {
                //게시판
                return new Fragment();
            }
            case 4:
            default: {
                //설정
                return Fragments.getSettingFragment();

            }
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

}
