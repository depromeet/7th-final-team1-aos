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
        loadSchedules(false, false);

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
        loadSchedules(false, true);
    }

    @Override
    public void loadMore() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        EspressoIdlingResource.increment();
        loadSchedules(true, false);
    }


    private void loadSchedules(boolean isMoreLoading, boolean isRefreshing) {
        repository.getScheduleList(isMoreLoading, new SchedulesRepository.GetScheduleListCallback() {

            @Override
            public void onSuccess(ArrayList<Schedule> scheduleList) {
                isLoading = false;
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }
                view.setCanLoadMore(scheduleList.size() == SchedulesRepository.SCEDULE_PER_PAGE);
                if (isMoreLoading) {
                    view.addSchedules(scheduleList);
                    return;
                }

                setIndicatorVisibility(isRefreshing, false);
                if (scheduleList.isEmpty())
                    view.showNoSchedules();
                else
                    view.showSchedules(scheduleList);

            }

            @Override
            public void onFailure(String code, String msg) {
                isLoading = false;
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;

                if (!isMoreLoading)
                    setIndicatorVisibility(isRefreshing, false);
                view.showToast(msg);
            }
        });
    }

    private void setIndicatorVisibility(boolean isRefreshing, boolean active) {
        if (isRefreshing) {
            view.setRefreshing(active);
        } else {
            view.setLoadingIndicator(active);
        }
    }

    //Todo(@hee: test용, 나중에 지우기!!)
    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }
}
