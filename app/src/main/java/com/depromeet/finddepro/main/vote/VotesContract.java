package com.depromeet.finddepro.main.vote;

import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;

public interface VotesContract {

	interface View {

		boolean isActive();

		void setLoadingIndicator(boolean active);

		void setVoteList(ArrayList<Vote> voteList);

		void showToast(String msg);
	}

	interface Presenter {

		void start();

		void refresh();

		void loadMore();

	}

}
