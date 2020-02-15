package com.depromeet.finddepro.main.vote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Choice;
import com.depromeet.finddepro.data.Vote;
import com.depromeet.finddepro.main.vote.adapter.VotesAdapter;
import com.depromeet.finddepro.main.vote.custom.ChoiceContainerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VotesFragment extends Fragment implements ChoiceContainerView.OnClickEventListener, VotesContract.View {

    @BindView(R.id.f_votes_rv)
    RecyclerView rvVotes;
    private Unbinder unbinder;

    private VotesAdapter adapter;
    private VotesContract.Presenter presenter;

    public VotesFragment() {
        presenter = new VotesPresenter(Injection.provideVotesRepository(), this);
        adapter = new VotesAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // @TODO : (jonghyo) 나중에 빼낼 것(Base 형태로라든가....)
        View root = inflater.inflate(R.layout.fragment_votes, container, false);
        unbinder = ButterKnife.bind(this, root);

        initRecyclerView();
        return root;
    }

    private void initRecyclerView() {
        rvVotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVotes.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.start();
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

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setVoteList(ArrayList<Vote> voteList) {
        adapter.setVotes(voteList);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
