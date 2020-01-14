package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.AttendanceInfo;

public class FakeAttendanceInfoDataSource implements AttendanceInfoDataSource {
    private static final int DELAY_TIME = 1000;

    private Handler handler;
    private AttendanceInfo fakeAttendanceInfo;

    public FakeAttendanceInfoDataSource() {
        handler = new Handler();
        initDummyAttendanceInfo();
    }

    private void initDummyAttendanceInfo() {
        fakeAttendanceInfo = new AttendanceInfo(14, 7, 2);

    }

    @Override
    public void getAttendanceInfo(GetAttendanceInfoCallback callback) {
        handler.postDelayed(() -> {
            callback.onSuccess(fakeAttendanceInfo);
        }, DELAY_TIME);
    }
}
