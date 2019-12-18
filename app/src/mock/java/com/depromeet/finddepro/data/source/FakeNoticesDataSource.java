package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

public class FakeNoticesDataSource implements NoticesDataSource {

    private static final int DELAY_TIME = 1000;

    private Handler handler;
    private ArrayList<Notice> fakeNoticeList;

    public FakeNoticesDataSource() {
        handler = new Handler();
        initDummyNotices(20);
    }

    private void initDummyNotices(int cnt) {
        fakeNoticeList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            fakeNoticeList.add(new Notice(i, i + "번째 공지사항", i + "번째 공지사항입니다. 확인!"));
        }
    }

    @Override
    public void getNoticeList(int idx, GetNoticeListCallback callback) {
        handler.postDelayed(() -> callback.onSuccess(fakeNoticeList), DELAY_TIME);
    }
}
