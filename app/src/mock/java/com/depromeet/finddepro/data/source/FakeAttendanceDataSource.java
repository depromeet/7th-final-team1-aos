package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.AttendanceInfo;

public class FakeAttendanceDataSource implements AttendanceDataSource {
    private static final int DELAY_TIME = 1000;

    private Handler handler;
    private AttendanceInfo fakeAttendanceInfo;
    private String result;

    public FakeAttendanceDataSource() {
        handler = new Handler();
        initDummyAttendanceInfo();
    }

    private void initDummyAttendanceInfo() {
        fakeAttendanceInfo = new AttendanceInfo(14, 7, 2);
        result = "checked";

    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {
        handler.postDelayed(() -> callback.onSuccess(fakeAttendanceInfo), DELAY_TIME);
    }

    @Override
    public void getCheckedAttendanceResult(double lat, double lon, String id, GetCheckedAttendanceCallback callback) {
        handler.postDelayed(() -> callback.onSuccess(result), DELAY_TIME);
    }
}
