package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;
import java.util.List;

public interface VotesDataSource {

    void getVoteList(long lastItemAt, int votePerPage, GetVoteListCallback callback);

    void addVote(Vote vote, CommonCallback callback);

    void updateVote(Vote vote, CommonCallback callback);

    void updateChoiceSelection(Vote vote, CommonCallback callback);

    void deleteVote(int voteId, CommonCallback callback);

    interface GetVoteListCallback {

        void onSuccess(ArrayList<Vote> voteList);

        void onFailure(String code, String msg);

    }

}
