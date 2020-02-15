package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.VotesDataSource;

public interface VotesRepository {
	void loadVotes(VotesDataSource.GetVoteListCallback callback);
}
