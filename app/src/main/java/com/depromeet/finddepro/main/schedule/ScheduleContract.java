package com.depromeet.finddepro.main.schedule;

import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

public interface ScheduleContract {
    interface View {
        void setLoadingIndicator(boolean active);

        void setLoadMore(boolean canLoadMore);

        void showNoSchedules();

        void showSchedules(ArrayList<Schedule> schedules);

        void addSchedules(ArrayList<Schedule> schedules);

        void clearSchedules();

        void showToast(int resId);

        void showToast(String msg);

        boolean isActive();
    }

    interface Presenter {

        void start();

        void refresh();

        void loadMore(int id);
    }
}
