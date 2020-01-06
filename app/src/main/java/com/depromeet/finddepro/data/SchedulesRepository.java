package com.depromeet.finddepro.data;


import java.util.ArrayList;

public interface SchedulesRepository {

    public static final int SCEDULE_PER_PAGE = 10;

    void getScheduleList(boolean isMoreLoading, GetScheduleListCallback callback);

    void clearCaches();
    interface GetScheduleListCallback {

        void onSuccess(ArrayList<Schedule> scheduleList);

        void onFailure(String code, String msg);

    }
}
