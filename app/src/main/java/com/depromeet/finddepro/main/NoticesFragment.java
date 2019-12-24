package com.depromeet.finddepro.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoticesFragment extends Fragment implements NoticesContract.View {

    private final NoticesContract.Presenter presenter;
    private Unbinder unbinder;

    @BindView(R.id.f_notices_pb)
    ProgressBar pb;
    @BindView(R.id.f_notices_rv)
    RecyclerView rvNotices;

    public NoticesFragment() {
        presenter = new NoticesPresenter(Injection.provideNoticesRepository(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notices, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder == null) {
            return;
        }

        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        pb.setVisibility(active ? View.VISIBLE : View.GONE);
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
        // @TODO : (jonghyo) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        // @TODO : (jonghyo) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
