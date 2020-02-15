package com.depromeet.finddepro.data.source;

import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;

public interface VotesDataSource {

    void getVoteList(long lastItemAt, int votePerPage, GetVoteListCallback callback);

    interface GetVoteListCallback {

        void onSuccess(ArrayList<Vote> voteList);

        void onFailure(String code, String msg);

    }

}
