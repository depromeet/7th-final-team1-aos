package com.depromeet.finddepro.data;

public interface AttendanceInfoRepository {
    void getAttendanceInfo(GetAttendanceInfoCallback callback);

    void clearCaches();

    interface GetAttendanceInfoCallback {
        void onSuccess(AttendanceInfo attendanceInfo);

        void onFailure(String code, String msg);
    }

}
