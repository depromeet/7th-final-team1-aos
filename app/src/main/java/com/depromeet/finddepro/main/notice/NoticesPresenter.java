package com.depromeet.finddepro.main.notice;

import com.depromeet.finddepro.data.Notice;
import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import java.util.ArrayList;

public class NoticesPresenter implements NoticesContract.Presenter {

    private final NoticesRepository repository;
    private final NoticesContract.View view;
    private boolean isLoading;

    NoticesPresenter(NoticesRepository repository, NoticesContract.View view) {
        this.repository = repository;
        this.view = view;
        this.isLoading = false;
    }

    @Override
    public void start() {
        isLoading = true;
        EspressoIdlingResource.increment();
        view.setLoadingIndicator(true);
        loadNotices(false, false);
    }

    @Override
    public void refresh() {
        if (isLoading) {
            view.setRefreshing(false);
            view.showToast("잠시 후 다시 시도해주세요.");
            return;
        }

        EspressoIdlingResource.increment();
        repository.clearCaches();
        loadNotices(false, true);
    }

    @Override
    public void loadMore() {
        if (isLoading) {
            return;
        }

        isLoading = true;
        EspressoIdlingResource.increment();
        loadNotices(true, false);
    }

    private void loadNotices(boolean isMoreLoading, boolean isRefreshing) {
        repository.getNoticeList(isMoreLoading, new NoticesRepository.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }

                view.setCanLoadMore(noticeList.size() == NoticesRepository.NOTICE_PER_PAGE);

                if (isMoreLoading) {
                    view.addNotices(noticeList);
                    return;
                }

                setIndicatorVisibility(isRefreshing, false);

                if (noticeList.isEmpty()) {
                    view.showNoNotices();
                } else {
                    view.showNotices(noticeList);
                }
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }

                if (!isMoreLoading) {
                    setIndicatorVisibility(isRefreshing, false);
                }

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
}
