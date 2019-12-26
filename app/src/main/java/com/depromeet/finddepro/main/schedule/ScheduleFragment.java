package com.depromeet.finddepro.main.schedule;

import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import android.widget.Toast;
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
        // @TODO : (hee) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        // @TODO : (hee) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
