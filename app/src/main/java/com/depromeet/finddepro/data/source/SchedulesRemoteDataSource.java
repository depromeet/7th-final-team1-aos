package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.network.ScheduleApi;

public class SchedulesRemoteDataSource implements SchedulesDataSource {

    private final ScheduleApi api;

    public SchedulesRemoteDataSource(ScheduleApi api) {
        this.api = api;
    }

    @Override
    public void getScheduleList(int idx, GetScheduleListCallback callback) {
        // @TODO : (hee) 실제 api 콜을 해야됨
    }
}
