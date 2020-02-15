package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.VotesDataSource;

import java.util.ArrayList;

class InMemoryVotesRepository implements VotesRepository {
    private final VotesDataSource votesDataSource;

    private ArrayList<Vote> cachedVotes;

    InMemoryVotesRepository(VotesDataSource votesDataSource) {
        this.votesDataSource = votesDataSource;
        this.cachedVotes = new ArrayList<>();
    }

    @Override
    public void clearCaches() {

    }

    @Override
    public void getVoteList(boolean isMoreLoading, VotesDataSource.GetVoteListCallback callback) {
        if (!cachedVotes.isEmpty() && !isMoreLoading) {
            ArrayList<Vote> voteList = new ArrayList<>(cachedVotes);
            callback.onSuccess(voteList);
            return;
        }

        long lastCreatedAt = isMoreLoading ? cachedVotes.get(cachedVotes.size() - 1).getCreatedAt() : 0;
        votesDataSource.getVoteList(lastCreatedAt, VOTE_PER_PAGE, new VotesDataSource.GetVoteListCallback() {
            @Override
            public void onSuccess(ArrayList<Vote> voteList) {
                cachedVotes.addAll(voteList);
                callback.onSuccess(voteList);
            }

            @Override
            public void onFailure(String code, String msg) {
                callback.onFailure(code, msg);
            }
        });
    }
}
