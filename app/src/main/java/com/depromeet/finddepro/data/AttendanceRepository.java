package com.depromeet.finddepro.data;

public interface AttendanceRepository {
    void getAttendanceInfo(GetAttendanceInfoCallback callback);

    void clearCaches();

    interface GetAttendanceInfoCallback {
        void onSuccess(AttendanceInfo attendanceInfo);

        void onFailure(String code, String msg);
    }

    void getCheckedAttendanceResult(double lat, double lon, String id, AttendanceRepository.GetCheckedAttendanceCallback callback);

    interface GetCheckedAttendanceCallback {
        void onSuccess(String result);

        void onFailure(String code, String msg);
    }
}
