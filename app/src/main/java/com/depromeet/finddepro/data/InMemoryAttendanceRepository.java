package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.AttendanceDataSource;

public class InMemoryAttendanceRepository implements AttendanceRepository {

    private final AttendanceDataSource attendanceInfoDataSource;
    private AttendanceInfo cashedAttendanceInfo;


    InMemoryAttendanceRepository(AttendanceDataSource attendanceInfoDataSource) {
        this.attendanceInfoDataSource = attendanceInfoDataSource;
        this.cashedAttendanceInfo = null;

    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {

        if (cashedAttendanceInfo != null) {
            callback.onSuccess(cashedAttendanceInfo);
            return;
        }

        attendanceInfoDataSource.getAttendanceInfo(new AttendanceDataSource.GetAttendanceInfoCallback() {
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

    @Override
    public void getCheckedAttendanceResult(double lat, double lon, String id, GetCheckedAttendanceCallback callback) {
        attendanceInfoDataSource.getCheckedAttendanceResult(lat, lon, id, new AttendanceDataSource.GetCheckedAttendanceCallback() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);

            }
        });
    }
}
