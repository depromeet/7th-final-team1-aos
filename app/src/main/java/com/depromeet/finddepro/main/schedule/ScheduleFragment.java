package com.depromeet.finddepro.main.schedule;

import androidx.fragment.app.Fragment;

import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment implements ScheduleContract.View {
    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setLoadMore(boolean canLoadMore) {

    }

    @Override
    public void showNoSchedules() {

    }

    @Override
    public void showSchedules(ArrayList<Schedule> schedules) {

    }

    @Override
    public void addSchedules(ArrayList<Schedule> schedules) {

    }

    @Override
    public void clearSchedules() {

    }

    @Override
    public void showToast(int resId) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
