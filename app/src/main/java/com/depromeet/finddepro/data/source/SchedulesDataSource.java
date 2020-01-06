package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

public interface SchedulesDataSource {
    void getScheduleList(int idx, int perPage, GetScheduleListCallback callback);

    interface GetScheduleListCallback {
        void onSuccess(ArrayList<Schedule> scheduleList);

        void onFailure(String code, String msg);
    }
}
