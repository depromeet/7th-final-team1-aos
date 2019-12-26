package com.depromeet.finddepro.main.schedule;

import com.depromeet.finddepro.data.Schedule;
import com.depromeet.finddepro.data.SchedulesRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import java.util.ArrayList;

public class SchedulePresenter implements ScheduleContract.Presenter {

    private final ScheduleContract.View view;
    private final SchedulesRepository repository;

    public SchedulePresenter(SchedulesRepository repository, ScheduleContract.View view) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
        EspressoIdlingResource.increment();
        repository.getScheduleList(0, new SchedulesRepository.GetScheduleListCallback() {

            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.showSchedules(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.showToast(msg);
            }
        });

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(int id) {

    }
}
