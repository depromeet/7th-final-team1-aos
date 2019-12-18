package com.depromeet.finddepro.main;

import androidx.fragment.app.Fragment;

import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

public class NoticesFragment extends Fragment implements NoticesContract.View {
    public NoticesFragment() {
    }


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setLoadMore(boolean canLoadMore) {

    }

    @Override
    public void showNoNotices() {

    }

    @Override
    public void showNotices(ArrayList<Notice> notices) {

    }

    @Override
    public void addNotices(ArrayList<Notice> notices) {

    }

    @Override
    public void clearNotices() {

    }

    @Override
    public void showToast(int resId) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
