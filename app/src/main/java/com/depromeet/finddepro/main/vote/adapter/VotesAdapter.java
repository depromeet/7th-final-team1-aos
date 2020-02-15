package com.depromeet.finddepro.main.vote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Vote;
import com.depromeet.finddepro.main.vote.custom.ChoiceContainerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.VoteViewHolder> {

    private List<Vote> votes;

    public VotesAdapter() {
        votes = new ArrayList<>();
    }

    public void setVotes(List<Vote> votes) {
        this.votes.clear();
        this.votes.addAll(votes);
        notifyDataSetChanged();
    }

    public void addVotes(List<Vote> votes) {
        this.votes.addAll(votes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        holder.bind(votes.get(position));
    }

    @Override
    public int getItemCount() {
        return votes.size();
    }

    class VoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_vote_iv_creator_profile)
        ImageView ivCreatorProfile;

        @BindView(R.id.item_vote_tv_creator_name)
        TextView tvCreatorName;

        @BindView(R.id.item_vote_tv_created_at)
        TextView tvCreatedAt;

        @BindView(R.id.item_vote_tv_title)
        TextView tvTitle;

        @BindView(R.id.item_vote_tv_content)
        TextView tvContent;

        @BindView(R.id.item_vote_tv_options)
        TextView tvOptions;

        @BindView(R.id.item_vote_choice_container)
        ChoiceContainerView ccv;

        @BindView(R.id.item_vote_btn_choice_complete)
        Button btnChoiceComplete;

        VoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Vote vote) {
            // @TODO : (jonghyo) 나중에 Glide 코드를 한 번 감쌀 필요가 있음
            Glide.with(ivCreatorProfile).load(vote.getCreatorProfileUrl()).circleCrop().into(ivCreatorProfile);
            tvCreatorName.setText(vote.getCreatorName());
            tvCreatedAt.setText(String.valueOf(vote.getCreatedAt()));
            tvTitle.setText(vote.getTitle());
            tvContent.setText(vote.getContent());
            tvOptions.setText(vote.getOptionStr());
            ccv.setChoiceList(vote.getChoices());
            btnChoiceComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // @TODO : (jonghyo) 선택했음을 알려줘야 됨
                }
            });

        }
    }

}
