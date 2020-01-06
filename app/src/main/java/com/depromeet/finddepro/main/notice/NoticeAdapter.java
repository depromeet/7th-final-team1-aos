package com.depromeet.finddepro.main.notice;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Notice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_MORE_LOADING = 0;
    private static final int ITEM_NOTICE = 1;

    private ArrayList<Notice> notices;

    private boolean canMoreLoading;

    NoticeAdapter() {
        notices = new ArrayList<>();
        canMoreLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasLoadingItem() && position == getItemCount() - 1) {
            return ITEM_MORE_LOADING;
        } else {
            return ITEM_NOTICE;
        }
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_MORE_LOADING:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, null, false));
            case ITEM_NOTICE:
            default:
                return new NoticeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, null, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_NOTICE:
                ((NoticeViewHolder) holder).bind(notices.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return hasLoadingItem() ? notices.size() + 1 : notices.size();
    }

    public void add(ArrayList<Notice> notices) {
        this.notices.addAll(notices);
        // @TODO : (jonghyo) 갱신 방식 바꿔야 됨
        notifyDataSetChanged();
    }

    void clear() {
        this.notices.clear();
    }

    private boolean hasLoadingItem() {
        return canMoreLoading;
    }

    boolean canLoadMore() {
        return canMoreLoading;
    }

    public void setCanLoadMore(boolean canMoreLoading) {
        this.canMoreLoading = canMoreLoading;
        notifyDataSetChanged();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        private final Unbinder unbinder;
        @BindView(R.id.item_notice_tv_title)
        TextView tvTitle;

        @BindView(R.id.item_notice_tv_content)
        TextView tvContent;

        @BindView(R.id.item_notice_tv_date)
        TextView tvDate;

        NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        void bind(Notice notice) {
            tvTitle.setText(notice.getTitle());
            tvContent.setText(notice.getContent());
            tvDate.setText(notice.getCreatedAtStr());
        }

        public Unbinder getUnbinder() {
            return unbinder;
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
