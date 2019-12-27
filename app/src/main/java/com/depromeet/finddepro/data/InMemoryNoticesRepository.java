package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.NoticesDataSource;

import java.util.ArrayList;

public class InMemoryNoticesRepository implements NoticesRepository {

    private final NoticesDataSource noticesDataSource;

    private ArrayList<Notice> cachedNotices;

    InMemoryNoticesRepository(NoticesDataSource noticesDataSource) {
        this.noticesDataSource = noticesDataSource;
        this.cachedNotices = new ArrayList<>();
    }

    @Override
    public void getNoticeList(boolean isMoreLoading, GetNoticeListCallback callback) {
        if (!cachedNotices.isEmpty() && !isMoreLoading) {
            ArrayList<Notice> noticeList = new ArrayList<>(cachedNotices);
            callback.onSuccess(noticeList);
            return;
        }

        int lastIdx = isMoreLoading ? cachedNotices.get(cachedNotices.size() - 1).getId() : 0;
        noticesDataSource.getNoticeList(lastIdx, NOTICE_PER_PAGE, new NoticesDataSource.GetNoticeListCallback() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                cachedNotices.addAll(noticeList);
                callback.onSuccess(noticeList);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);
            }
        });
    }

    @Override
    public void clearCaches() {
        cachedNotices.clear();
    }

    private int getCachedLastIdx(int idx, int perPage) {
        return idx + perPage >= cachedNotices.size() ?
                cachedNotices.size() - 1 :
                idx + perPage;
    }
}
