package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.network.NoticeApi;

public class NoticesRemoteDataSource implements NoticesDataSource {
    private final NoticeApi api;

    public NoticesRemoteDataSource(NoticeApi api) {

        this.api = api;
    }

    @Override
    public void getNoticeList(int idx, int perPage, GetNoticeListCallback callback) {
        // @TODO : (jonghyo) 실제 api 콜을 해야됨
    }
}
