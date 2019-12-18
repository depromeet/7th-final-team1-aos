package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

public interface NoticesRepository {

    void getNoticeList(int idx, GetNoticeListCallback callback);

    interface GetNoticeListCallback {

        void onSuccess(ArrayList<Notice> noticeList);

        void onFailure(String code, String msg);

    }

}
