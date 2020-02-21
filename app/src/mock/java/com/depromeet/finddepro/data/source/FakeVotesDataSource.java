package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.Choice;
import com.depromeet.finddepro.data.User;
import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;

public class FakeVotesDataSource implements VotesDataSource {

    private static final int DELAY_TIME = 1000;

    private Handler handler;
    private ArrayList<Vote> fakeVoteList;

    public FakeVotesDataSource() {
        handler = new Handler();
        initDummyVotes(25);
    }

    private void initDummyVotes(int cnt) {
        fakeVoteList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            ArrayList<Choice> dummyChoice = new ArrayList<>();
            dummyChoice.add(new Choice("1", "1번째 선택지", true, 10, new ArrayList<>()));
            dummyChoice.add(new Choice("2", "2번째 선택지", false, 8, new ArrayList<>()));
            dummyChoice.add(new Choice("3", "3번째 선택지", true, 5, new ArrayList<>()));
            dummyChoice.add(new Choice("4", "4번째 선택지", false, 6, new ArrayList<>()));
            dummyChoice.add(new Choice("5", "5번째 선택지", true, 7, new ArrayList<>()));
            Vote vote = new Vote(i,
                    i + "번째 투표",
                    i + "번째 점심 뭐 먹으러 갈래?",
                    new User(String.valueOf(1), "관리자", "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"),
                    dummyChoice,
                    3,
                    5,
                    20,
                    2,
                    1577327181000L - cnt + i,
                    1577327181000L);
            fakeVoteList.add(vote);
        }
    }

    @Override
    public void getVoteList(long lastItemAt, int votePerPage, GetVoteListCallback callback) {
        handler.postDelayed(() -> {
            int firstIdx = lastItemAt == 0 ? 0 : getVoteId(lastItemAt) + 1;
            int perPage = 5;

            int lastIdx = firstIdx + perPage >= fakeVoteList.size() ?
                    fakeVoteList.size() :
                    firstIdx + perPage;
            callback.onSuccess(new ArrayList<>(fakeVoteList.subList(firstIdx, lastIdx)));
        }, DELAY_TIME);
    }

    @Override
    public void addVote(Vote vote, CommonCallback callback) {

    }

    @Override
    public void updateVote(Vote vote, CommonCallback callback) {

    }

    @Override
    public void updateChoiceSelection(Vote vote, CommonCallback callback) {

    }

    @Override
    public void deleteVote(int voteId, CommonCallback callback) {

    }

    private int getVoteId(long createdAt) {
        for (int i = 0; i < fakeVoteList.size(); i++) {
            if (fakeVoteList.get(i).getCreatedAt() == createdAt) {
                return i;
            }
        }
        return fakeVoteList.size();
    }
}
