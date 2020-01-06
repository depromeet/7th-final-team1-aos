package com.depromeet.finddepro.main.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoticesFragment extends Fragment implements NoticesContract.View, SwipeRefreshLayout.OnRefreshListener {

    private final NoticesContract.Presenter presenter;
    private Unbinder unbinder;

    @BindView(R.id.f_notices_pb)
    ProgressBar pb;
    @BindView(R.id.f_notices_srl)
    SwipeRefreshLayout srlNotices;
    @BindView(R.id.f_notices_rv)
    RecyclerView rvNotices;

    private NoticeAdapter noticeAdapter;

    public NoticesFragment() {
        presenter = new NoticesPresenter(Injection.provideNoticesRepository(), this);
        noticeAdapter = new NoticeAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notices, container, false);
        unbinder = ButterKnife.bind(this, root);

        initRecyclerView();

        return root;
    }

    private void initRecyclerView() {
        rvNotices.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotices.setAdapter(noticeAdapter);

        rvNotices.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvNotices.getLayoutManager();
                if (needToIgnoreMoreLoadingRequest(layoutManager)) {
                    return;
                }

                presenter.loadMore();
            }
        });

        srlNotices.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder == null) {
            return;
        }

        unbinder.unbind();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        pb.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        srlNotices.setRefreshing(isRefreshing);
    }

    @Override
    public void setCanLoadMore(boolean canLoadMore) {
        noticeAdapter.setCanLoadMore(canLoadMore);
    }

    @Override
    public void showNoNotices() {
    }

    @Override
    public void showNotices(ArrayList<Notice> notices) {
        noticeAdapter.clear();
        noticeAdapter.add(notices);
    }

    @Override
    public void addNotices(ArrayList<Notice> notices) {
        noticeAdapter.add(notices);
    }

    @Override
    public void clearNotices() {
        noticeAdapter.clear();
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

    private boolean needToIgnoreMoreLoadingRequest(LinearLayoutManager layoutManager) {
        return layoutManager == null || !noticeAdapter.canLoadMore() || layoutManager.findLastVisibleItemPosition() < noticeAdapter.getItemCount() - 1;
    }

    @Override
    public void onRefresh() {
        if (presenter == null) {
            return;
        }

        presenter.refresh();
    }
}
