package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.SchedulesDataSource;

import java.util.ArrayList;

public class InMemorySchedulesRepository implements SchedulesRepository {

    private final SchedulesDataSource schedulesDataSource;
    private ArrayList<Schedule> cachedSchedules;

    InMemorySchedulesRepository(SchedulesDataSource schedulesDataSource) {
        this.schedulesDataSource = schedulesDataSource;
        this.cachedSchedules = new ArrayList<>();
    }

    @Override
    public void getScheduleList(boolean isMoreLoading, GetScheduleListCallback callback) {
        if (!cachedSchedules.isEmpty() && !isMoreLoading) {
            ArrayList<Schedule> scheduleList = new ArrayList<>(cachedSchedules);
            callback.onSuccess(scheduleList);
            return;
        }

        int lastIdx = isMoreLoading ? cachedSchedules.get(cachedSchedules.size() - 1).getId() : 0;
        schedulesDataSource.getScheduleList(lastIdx, SCEDULE_PER_PAGE, new SchedulesDataSource.GetScheduleListCallback() {
            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                cachedSchedules.addAll(scheduleList);
                callback.onSuccess(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);

            }
        });

    }

    @Override
    public void clearCaches() {
        cachedSchedules.clear();
    }

    private int getCachedLastIdx(int idx, int perPage) {
        return idx + perPage >= cachedSchedules.size() ? cachedSchedules.size() - 1 : idx + perPage;
    }
}
