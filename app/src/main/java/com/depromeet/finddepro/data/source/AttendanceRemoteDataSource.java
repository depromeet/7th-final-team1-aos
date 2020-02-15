package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.network.AttendanceInfoApi;
import com.depromeet.finddepro.network.CheckAttendanceApi;

public class AttendanceRemoteDataSource implements AttendanceDataSource {
    private AttendanceInfoApi attendanceInfoApi;
    private CheckAttendanceApi checkAttendanceApi;

    public AttendanceRemoteDataSource(AttendanceInfoApi attendanceInfoApi, CheckAttendanceApi checkAttendanceApi) {
        this.attendanceInfoApi = attendanceInfoApi;
        this.checkAttendanceApi = checkAttendanceApi;

    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {
        // @TODO : (hee) 실제 api 콜을 해야됨

    }


    @Override
    public void getCheckedAttendanceResult(double lat, double lon, String id, AttendanceDataSource.GetCheckedAttendanceCallback callback) {
        // @TODO : (hee) 실제 api 콜을 해야됨

    }


}
