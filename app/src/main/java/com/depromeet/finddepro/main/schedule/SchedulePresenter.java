package com.depromeet.finddepro.main.schedule;

import com.depromeet.finddepro.data.Schedule;
import com.depromeet.finddepro.data.SchedulesRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import java.util.ArrayList;

public class SchedulePresenter implements ScheduleContract.Presenter {

    private final ScheduleContract.View view;
    private final SchedulesRepository repository;
    private boolean isLoading;

    public SchedulePresenter(SchedulesRepository repository, ScheduleContract.View view) {
        this.view = view;
        this.repository = repository;
        this.isLoading = false;
    }

    @Override
    public void start() {
        isLoading = true;
        EspressoIdlingResource.increment();
        view.setLoadingIndicator(true);
        repository.getScheduleList(false, new SchedulesRepository.GetScheduleListCallback() {

            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }
                view.setCanLoadMore(scheduleList.size() == SchedulesRepository.SCEDULE_PER_PAGE);
                view.setLoadingIndicator(false);
                view.showSchedules(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }
                view.setLoadingIndicator(false);
                view.showToast(msg);
            }
        });

    }

    @Override
    public void refresh() {
        if (isLoading) {
            view.setRefreshing(false);
            view.showToast("로딩중..잠시 후 다시 시도해주세요.");
            return;
        }
        EspressoIdlingResource.increment();
        repository.clearCaches();
        repository.getScheduleList(false, new SchedulesRepository.GetScheduleListCallback() {
            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }
                view.setCanLoadMore(scheduleList.size() == SchedulesRepository.SCEDULE_PER_PAGE);
                view.setLoadingIndicator(false);
                view.showSchedules(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.setRefreshing(false);
                view.showToast(msg);
            }
        });

    }

    @Override
    public void loadMore() {
        // @TODO : (hee) 이 부분 중복이 많음으로 개선해야 됨
        if (isLoading) {
            return;
        }
        isLoading = true;
        EspressoIdlingResource.increment();
        repository.getScheduleList(true, new SchedulesRepository.GetScheduleListCallback() {

            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                isLoading = false;
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }
                view.setCanLoadMore(scheduleList.size() == SchedulesRepository.SCEDULE_PER_PAGE);
                view.addSchedules(scheduleList);
            }

            @Override
            public void onFailure(String code, String msg) {
                isLoading = false;
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }
                view.showToast(msg);
            }
        });
    }
}
