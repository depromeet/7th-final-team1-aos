package com.depromeet.finddepro.main.schedule;

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
import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScheduleFragment extends Fragment implements ScheduleContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    private final ScheduleContract.Presenter presenter;
    @BindView(R.id.f_schedules_pb)
    ProgressBar pb;
    @BindView(R.id.f_schedules_srl)
    SwipeRefreshLayout srlSchedules;
    @BindView(R.id.f_schedules_rv)
    RecyclerView rvSchedules;
    private ScheduleAdapter scheduleAdapter;
    private Unbinder unbinder;


    public ScheduleFragment() {
        presenter = new SchedulePresenter(Injection.provideSchedulesRepository(), this);
        scheduleAdapter = new ScheduleAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        unbinder = ButterKnife.bind(this, root);

        initRecyclerView();

        return root;
    }

    private void initRecyclerView() {
        rvSchedules.setHasFixedSize(true);
        rvSchedules.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSchedules.setAdapter(scheduleAdapter);

        rvSchedules.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) rvSchedules.getLayoutManager();
                if (needToIgnoreMoreLoadingRequest(layoutManager)) {
                    return;
                }

                presenter.loadMore();
            }
        });

        srlSchedules.setOnRefreshListener(this);
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
        srlSchedules.setRefreshing(isRefreshing);

    }

    @Override
    public void setCanLoadMore(boolean canLoadMore) {
        scheduleAdapter.setCanLoadMore(canLoadMore);

    }

    @Override
    public void showNoSchedules() {

    }

    @Override
    public void showSchedules(ArrayList<Schedule> schedules) {
        scheduleAdapter.clear();
        scheduleAdapter.add(schedules);
    }

    @Override
    public void addSchedules(ArrayList<Schedule> schedules) {
        scheduleAdapter.add(schedules);
    }

    @Override
    public void clearSchedules() {
        scheduleAdapter.clear();
    }

    @Override
    public void showToast(int resId) {
        // @TODO : (hee) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        // @TODO : (hee) Toast를 한 곳에서 관리하도록 설정할 필요가 있음
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {
        if (presenter == null) {
            return;
        }

        presenter.refresh();
    }


    private boolean needToIgnoreMoreLoadingRequest(LinearLayoutManager layoutManager) {
        return layoutManager == null || !scheduleAdapter.canLoadMore() || layoutManager.findLastVisibleItemPosition() < scheduleAdapter.getItemCount() - 1;
    }

}
