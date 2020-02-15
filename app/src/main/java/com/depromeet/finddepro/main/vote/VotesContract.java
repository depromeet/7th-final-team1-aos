package com.depromeet.finddepro.main.vote;

import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;

public interface VotesContract {

	interface View {

		boolean isActive();

		void setLoadingIndicator(boolean active);

		void setRefreshing(boolean isRefreshing);

		void setCanLoadMore(boolean canLoadMore);

		void showNoNotices();

		void showToast(String msg);

		void addVotes(ArrayList<Vote> voteList);

		void showNotices(ArrayList<Vote> voteList);
	}

	interface Presenter {

		void start();

		void refresh();

		void loadMore();

	}

}
