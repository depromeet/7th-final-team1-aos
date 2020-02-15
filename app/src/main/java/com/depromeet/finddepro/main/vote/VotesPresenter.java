package com.depromeet.finddepro.main.vote;

import com.depromeet.finddepro.data.Vote;
import com.depromeet.finddepro.data.VotesRepository;
import com.depromeet.finddepro.data.source.VotesDataSource;

import java.util.ArrayList;

public class VotesPresenter implements VotesContract.Presenter {

    private final VotesRepository repository;
    private final VotesContract.View view;

    VotesPresenter(VotesRepository repository, VotesContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void start() {
        view.setLoadingIndicator(true);
        repository.loadVotes(new VotesDataSource.GetVoteListCallback() {
            @Override
            public void onSuccess(ArrayList<Vote> voteList) {
                if (!view.isActive()) {
                    return;
                }

                view.setLoadingIndicator(false);
                view.setVoteList(voteList);
            }

            @Override
            public void onFailure(String code, String msg) {
                if (!view.isActive()) {
                    return;
                }

                view.setLoadingIndicator(false);
                view.showToast(msg);
            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }
}
