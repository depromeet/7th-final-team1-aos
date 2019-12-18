package com.depromeet.finddepro.main;

import com.depromeet.finddepro.data.Notice;
import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import java.util.ArrayList;

public class NoticesPresenter implements NoticesContract.Presenter {

    private final NoticesRepository repository;
    private final NoticesContract.View view;

    public NoticesPresenter(NoticesRepository repository, NoticesContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void start() {
        EspressoIdlingResource.increment();
        repository.getNoticeList(0, new NoticesRepository.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                EspressoIdlingResource.decrement();
                if (!view.isActive()) {
                    return;
                }

                view.showNotices(noticeList);
            }

            @Override
            public void onFailure(String code, String msg) {
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
