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
        repository.getNoticeList(false, new NoticesRepository.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }

                view.setCanLoadMore(noticeList.size() == NoticesRepository.NOTICE_PER_PAGE);
                view.setLoadingIndicator(false);
                view.showNotices(noticeList);
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
            view.showToast("잠시 후 다시 시도해주세요.");
            return;
        }

        EspressoIdlingResource.increment();
        repository.clearCaches();
        repository.getNoticeList(false, new NoticesRepository.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.setCanLoadMore(noticeList.size() == NoticesRepository.NOTICE_PER_PAGE);
                view.setRefreshing(false);
                view.showNotices(noticeList);
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
        // @TODO : (jonghyo) 이 부분 중복이 많음으로 개선해야 됨
        if (isLoading) {
            return;
        }

        isLoading = true;
        EspressoIdlingResource.increment();
        repository.getNoticeList(true, new NoticesRepository.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                isLoading = false;
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.setCanLoadMore(noticeList.size() == NoticesRepository.NOTICE_PER_PAGE);
                view.addNotices(noticeList);
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
