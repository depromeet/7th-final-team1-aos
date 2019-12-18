package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.NoticesDataSource;

import java.util.ArrayList;

public class InMemoryNoticesRepository implements NoticesRepository {

    private final NoticesDataSource noticesDataSource;

    InMemoryNoticesRepository(NoticesDataSource noticesDataSource) {
        this.noticesDataSource = noticesDataSource;
    }

    @Override
    public void getNoticeList(int idx, GetNoticeListCallback callback) {
        noticesDataSource.getNoticeList(idx, new NoticesDataSource.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                callback.onSuccess(noticeList);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);
            }
        });
    }
}
