package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.AttendanceInfoDataSource;

public class InMemoryAttendanceInfoRepository implements AttendanceInfoRepository {

    private final AttendanceInfoDataSource attendanceInfoDataSource;
    private AttendanceInfo cashedAttendanceInfo;


    InMemoryAttendanceInfoRepository(AttendanceInfoDataSource attendanceInfoDataSource) {
        this.attendanceInfoDataSource = attendanceInfoDataSource;
        this.cashedAttendanceInfo = null;

    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {

        if (cashedAttendanceInfo != null) {
            callback.onSuccess(cashedAttendanceInfo);
            return;
        }

        attendanceInfoDataSource.getAttendanceInfo(new AttendanceInfoDataSource.GetAttendanceInfoCallback() {
            @Override
            public void onSuccess(AttendanceInfo attendanceInfo) {
                cashedAttendanceInfo = attendanceInfo;
                callback.onSuccess(attendanceInfo);

            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);

            }
        });
    }

    @Override
    public void clearCaches() {
        cashedAttendanceInfo = null;
    }
}
