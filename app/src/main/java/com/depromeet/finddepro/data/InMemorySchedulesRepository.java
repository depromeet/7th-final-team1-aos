package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.SchedulesDataSource;

import java.util.ArrayList;

public class InMemorySchedulesRepository implements SchedulesRepository {

    private final SchedulesDataSource schedulesDataSource;

    InMemorySchedulesRepository(SchedulesDataSource schedulesDataSource) {
        this.schedulesDataSource = schedulesDataSource;
    }

    @Override
    public void getScheduleList(int idx, GetScheduleListCallback callback) {
        schedulesDataSource.getScheduleList(idx, new SchedulesDataSource.GetScheduleListCallback() {

            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                callback.onSuccess(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);
            }
        });
    }
}
