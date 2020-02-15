package com.depromeet.finddepro.main.vote;

import com.depromeet.finddepro.data.Vote;
import com.depromeet.finddepro.data.VotesRepository;
import com.depromeet.finddepro.data.source.VotesDataSource;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import java.util.ArrayList;

public class VotesPresenter implements VotesContract.Presenter {

    private final VotesRepository repository;
    private final VotesContract.View view;
    private boolean isLoading;

    VotesPresenter(VotesRepository repository, VotesContract.View view) {
        this.repository = repository;
        this.view = view;
        this.isLoading = false;
    }

    @Override
    public void start() {
        isLoading = true;
        EspressoIdlingResource.increment();
        view.setLoadingIndicator(true);
        loadVotes(false, false);
    }

    @Override
    public void refresh() {
        if (isLoading) {
            view.setRefreshing(false);
            view.showToast("잠시 후 다시 시도해주세요.");
            return;
        }

        EspressoIdlingResource.increment();
        repository.clearCaches();
        loadVotes(false, true);
    }

    @Override
    public void loadMore() {
        if (isLoading) {
            return;
        }

        isLoading = true;
        EspressoIdlingResource.increment();
        loadVotes(true, false);
    }

    private void loadVotes(boolean isMoreLoading, boolean isRefreshing) {
        repository.getVoteList(isMoreLoading, new VotesDataSource.GetVoteListCallback() {
            @Override
            public void onSuccess(ArrayList<Vote> voteList) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }

                view.setCanLoadMore(voteList.size() == VotesRepository.VOTE_PER_PAGE);

                if (isMoreLoading) {
                    view.addVotes(voteList);
                    return;
                }

                setIndicatorVisibility(isRefreshing, false);

                if (voteList.isEmpty()) {
                    view.showNoNotices();
                } else {
                    view.showNotices(voteList);
                }
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                isLoading = false;
                if (!view.isActive()) {
                    return;
                }

                if (!isMoreLoading) {
                    setIndicatorVisibility(isRefreshing, false);
                }

                view.showToast(msg);
            }
        });
    }

    private void setIndicatorVisibility(boolean isRefreshing, boolean active) {
        if (isRefreshing) {
            view.setRefreshing(active);
        } else {
            view.setLoadingIndicator(active);
        }
    }
}
