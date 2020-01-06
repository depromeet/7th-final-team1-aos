package com.depromeet.finddepro.main.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Schedule;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_MORE_LOADING = 0;
    private static final int ITEM_SCHEDULE = 1;
    private ArrayList<Schedule> schedules;
    private boolean canMoreLoading;

    ScheduleAdapter() {
        schedules = new ArrayList<>();
        canMoreLoading = false;
    }

    @Override
    public int getItemViewType(int position) {      //position에 해당하는 view 타입 리턴
        if (hasLoadingItem() && position == getItemCount() - 1) {
            return ITEM_MORE_LOADING;
        } else {
            return ITEM_SCHEDULE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_MORE_LOADING:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, null, false));
            case ITEM_SCHEDULE:
            default:
                return new ScheduleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, null, false));

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_SCHEDULE:
                ((ScheduleViewHolder) holder).bind(schedules.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return hasLoadingItem() ? schedules.size() + 1 : schedules.size();
    }

    @Override
    public long getItemId(int position) {
        return schedules.get(position).getId();
    }

    public void add(ArrayList<Schedule> schedules) {
        this.schedules.addAll(schedules);
        // @TODO : (hee) 갱신 방식 바꿔야 됨
        notifyDataSetChanged();
    }

    public void clear() {
        this.schedules.clear();
    }

    private boolean hasLoadingItem() {
        return canMoreLoading;
    }

    public boolean canLoadMore() {
        return canMoreLoading;
    }

    public void setCanLoadMore(boolean canMoreLoading) {
        this.canMoreLoading = canMoreLoading;
        notifyDataSetChanged();
    }

    //viewholder
    class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private final Unbinder unbinder;
        @BindView(R.id.item_schedule_tv_date)
        TextView tvDate;
        @BindView(R.id.item_schedule_tv_content)
        TextView tvContent;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        void bind(Schedule schedule) {
            tvContent.setText(schedule.getContent());
            tvDate.setText(schedule.getWeeksStr());
        }

        public Unbinder getUnbinder() {
            return unbinder;
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
