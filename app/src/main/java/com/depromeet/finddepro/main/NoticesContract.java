package com.depromeet.finddepro.main;

import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

public interface NoticesContract {

    interface View {

        void setLoadingIndicator(boolean active);

        void setRefreshing(boolean isRefreshing);

        void setCanLoadMore(boolean canLoadMore);

        void showNoNotices();

        void showNotices(ArrayList<Notice> notices);

        void addNotices(ArrayList<Notice> notices);

        void clearNotices();

        void showToast(int resId);

        void showToast(String msg);

        boolean isActive();

    }

    interface Presenter {

        void start();

        void refresh();

        void loadMore();

    }

}
