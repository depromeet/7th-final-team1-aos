package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.data.AttendanceInfo;

public interface AttendanceDataSource {

    void getAttendanceInfo(GetAttendanceInfoCallback callback);

    void getCheckedAttendanceResult(double lat, double lon, String id, GetCheckedAttendanceCallback callback);


    interface GetAttendanceInfoCallback {
        void onSuccess(AttendanceInfo attendanceInfo);

        void onFailure(String code, String msg);
    }

    interface GetCheckedAttendanceCallback {
        void onSuccess(String result);

        void onFailure(String code, String msg);
    }
}
