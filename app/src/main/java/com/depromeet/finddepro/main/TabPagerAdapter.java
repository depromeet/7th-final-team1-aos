//package com.depromeet.finddepro.main;
//
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import com.depromeet.finddepro.main.attendance.AttendanceInfoFragment;
//
//public class TabPagerAdapter extends FragmentPagerAdapter {
//
//    private static final int PAGE_NUMBER = 4;
//
//    TabPagerAdapter(FragmentManager fragmentManager) {
//        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0: {
//                AttendanceInfoFragment attendanceInfoFragment = new AttendanceInfoFragment();
//                //
//                return attendanceInfoFragment;
//            }
//            case 1: {
//                AttendanceInfoFragment attendanceInfoFragment = new AttendanceInfoFragment();
//                return attendanceInfoFragment;
//            }
//            case 2: {
//                AttendanceInfoFragment attendanceInfoFragment = new AttendanceInfoFragment();
//                return attendanceInfoFragment;
//            }
//            case 3:
//            default: {
//                AttendanceInfoFragment attendanceInfoFragment = new AttendanceInfoFragment();
//                return attendanceInfoFragment;
//            }
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return PAGE_NUMBER;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "홈";
//            case 1:
//                return "출석";
//            case 2:
//                return "투표";
//            case 3:
//                return "게시판";
//            default:
//                return "Default";
//        }
//    }
//}
