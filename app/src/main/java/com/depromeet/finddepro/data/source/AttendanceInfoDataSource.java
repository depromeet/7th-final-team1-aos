package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.data.AttendanceInfo;

public interface AttendanceInfoDataSource {

    void getAttendanceInfo(GetAttendanceInfoCallback callback);

    interface GetAttendanceInfoCallback {
        void onSuccess(AttendanceInfo attendanceInfo);

        void onFailure(String code, String msg);
    }
}
