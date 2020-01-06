package com.depromeet.finddepro.data;

import java.util.ArrayList;

public interface NoticesRepository {

    int NOTICE_PER_PAGE = 10;

    void getNoticeList(boolean isMoreLoading, GetNoticeListCallback callback);

    void clearCaches();

    interface GetNoticeListCallback {

        void onSuccess(ArrayList<Notice> noticeList);

        void onFailure(String code, String msg);

    }

}
