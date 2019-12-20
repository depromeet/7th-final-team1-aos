package com.depromeet.finddepro.data;


import java.util.ArrayList;

public interface SchedulesRepository {

    void getScheduleList(int idx, GetScheduleListCallback callback);

    interface GetScheduleListCallback {

        void onSuccess(ArrayList<Schedule> scheduleList);

        void onFailure(String code, String msg);

    }
}
