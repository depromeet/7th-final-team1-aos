package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.VotesDataSource;

class InMemoryVotesRepository implements VotesRepository {
    private final VotesDataSource votesDataSource;

    public InMemoryVotesRepository(VotesDataSource votesDataSource) {
        this.votesDataSource = votesDataSource;
    }


    @Override
    public void loadVotes(VotesDataSource.GetVoteListCallback callback) {
        votesDataSource.getVoteList(0L, callback);
    }
}
