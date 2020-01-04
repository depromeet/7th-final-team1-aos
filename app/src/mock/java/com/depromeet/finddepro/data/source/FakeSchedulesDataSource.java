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
        for (int i = 1; i <= cnt; i++) {
            fakeScheduleList.add(new Schedule(i, 1577327181000L, i + "일정내용입니다."));
        }
    }

    @Override
    public void getScheduleList(int idx, int perPage, GetScheduleListCallback callback) {
        handler.postDelayed(() -> {
            int firstIdx = idx == 0 ? 0 : idx + 1;

            int lastIdx = firstIdx + perPage >= fakeScheduleList.size() ?
                    fakeScheduleList.size() :
                    firstIdx + perPage;
            callback.onSuccess(new ArrayList<>(fakeScheduleList.subList(firstIdx, lastIdx)));
        }, DELAY_TIME);

    }
}
