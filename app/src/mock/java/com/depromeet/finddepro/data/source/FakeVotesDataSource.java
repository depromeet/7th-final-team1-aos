package com.depromeet.finddepro.data.source;

import android.os.Handler;

import com.depromeet.finddepro.data.Choice;
import com.depromeet.finddepro.data.User;
import com.depromeet.finddepro.data.Vote;

import java.util.ArrayList;
import java.util.List;

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
                    new User(String.valueOf(1), "관리자", ""),
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
    public void getVoteList(long lastItemAt, GetVoteListCallback callback) {
//        handler.postDelayed(() -> {
//            int firstIdx = idx == 0 ? 0 : idx + 1;
//
//            int lastIdx = firstIdx + perPage >= fakeVoteList.size() ?
//                    fakeVoteList.size() :
//                    firstIdx + perPage;
//            callback.onSuccess(new ArrayList<>(fakeVoteList.subList(firstIdx, lastIdx)));
//        }, DELAY_TIME);
    }
}
