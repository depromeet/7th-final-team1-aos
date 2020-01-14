package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.network.AttendanceInfoApi;

public class AttendanceInfoRemoteDataSource implements AttendanceInfoDataSource {
    private AttendanceInfoApi api;

    public AttendanceInfoRemoteDataSource(AttendanceInfoApi api) {
        this.api = api;
    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {
        // @TODO : (hee) 실제 api 콜을 해야됨

    }
}
