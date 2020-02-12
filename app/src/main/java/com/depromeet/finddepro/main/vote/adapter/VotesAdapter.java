package com.depromeet.finddepro.main.vote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.R;

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.VoteViewHolder> {

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote, );
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VoteViewHolder extends RecyclerView.ViewHolder {

        public VoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
