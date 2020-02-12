package com.depromeet.finddepro.main.vote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Choice;
import com.depromeet.finddepro.main.vote.custom.ChoiceContainerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VotesFragment extends Fragment implements ChoiceContainerView.OnClickEventListener {

    @BindView(R.id.f_votes_rv)
    RecyclerView rvVotes;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // @TODO : (jonghyo) 나중에 빼낼 것(Base 형태로라든가....)
        View root = inflater.inflate(R.layout.fragment_votes, container, true);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Choice> dummyChoice = new ArrayList<>();
        dummyChoice.add(new Choice("1", "1번째 선택지", true, 10, new ArrayList<>()));
        dummyChoice.add(new Choice("2", "2번째 선택지", false, 8, new ArrayList<>()));
        dummyChoice.add(new Choice("3", "3번째 선택지", true, 5, new ArrayList<>()));
        dummyChoice.add(new Choice("4", "4번째 선택지", false, 6, new ArrayList<>()));
        dummyChoice.add(new Choice("5", "5번째 선택지", true, 7, new ArrayList<>()));
//        rvVotes.setChoiceList(dummyChoice);
//        rvVotes.setListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder == null) {
            return;
        }

        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onChangedChoice(Choice choice) {
        Toast.makeText(getContext(), choice.getName() + "클릭", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickPerson(Choice choice) {
        Toast.makeText(getContext(), choice.getName() + "사람 클릭", Toast.LENGTH_SHORT).show();
    }
}
