package com.depromeet.finddepro.data;

import com.depromeet.finddepro.data.source.VotesDataSource;

public interface VotesRepository {
	public static final int VOTE_PER_PAGE = 5;

	void clearCaches();

	void getVoteList(boolean isMoreLoading, VotesDataSource.GetVoteListCallback callback);
}
