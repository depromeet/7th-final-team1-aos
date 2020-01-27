package com.depromeet.finddepro.main.vote;

public interface VotesContract {

	interface View {

		boolean isActive();

		void setLoadingIndicator(boolean active);

	}

	interface Presenter {

		void start();

		void refresh();

		void loadMore();

	}

}
