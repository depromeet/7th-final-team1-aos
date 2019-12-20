package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

public class FakeSchedulesDataSource implements SchedulesDataSource {
    private static final int DELAY_TIME = 1000;
    private Handler handler;
    private ArrayList<Schedule> fakeScheduleList;

    public FakeSchedulesDataSource() {
        handler = new Handler();
        initDummySchedules(20);

    }

    private void initDummySchedules(int cnt) {
        fakeScheduleList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            fakeScheduleList.add(new Schedule(i, i, "2019-12-20", i + "주차 일정입니다. 확인!"));
        }
    }

    @Override
    public void getScheduleList(int idx, GetScheduleListCallback callback) {
        handler.postDelayed(() -> callback.onSuccess(fakeScheduleList), DELAY_TIME);

    }
}
