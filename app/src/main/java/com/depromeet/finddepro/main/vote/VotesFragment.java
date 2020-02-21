package com.depromeet.finddepro.main.vote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Choice;
import com.depromeet.finddepro.data.Vote;
import com.depromeet.finddepro.main.vote.adapter.VotesAdapter;
import com.depromeet.finddepro.main.vote.custom.ChoiceContainerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VotesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ChoiceContainerView.OnClickEventListener, VotesContract.View {

	@BindView(R.id.f_votes_rv)
	RecyclerView rvVotes;
	@BindView(R.id.f_votes_pb)
	ProgressBar pb;
	@BindView(R.id.f_votes_srl)
	SwipeRefreshLayout srlVotes;

	@BindView(R.id.f_votes_tv_now)
	TextView mTvNow;
	@BindView(R.id.f_votes_tv_end)
	TextView mTvEnd;
	@BindView(R.id.f_votes_tv_all)
	TextView mTvAll;

	private Unbinder unbinder;

	private VotesAdapter adapter;
	private VotesContract.Presenter presenter;

	public VotesFragment() {
		presenter = new VotesPresenter(Injection.provideVotesRepository(), this);
		adapter = new VotesAdapter();
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

		rvVotes.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				LinearLayoutManager layoutManager = (LinearLayoutManager) rvVotes.getLayoutManager();
				if (needToIgnoreMoreLoadingRequest(layoutManager)) {
					return;
				}

				presenter.loadMore();
			}
		});

		srlVotes.setOnRefreshListener(this);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		presenter.start();
		mTvNow.setSelected(true);
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

	@OnClick({R.id.f_votes_tv_now})
	public void onClickNow() {
		mTvNow.setSelected(true);
		mTvEnd.setSelected(false);
		mTvAll.setSelected(false);
	}

	@OnClick({R.id.f_votes_tv_end})
	public void onClickEnd() {
		mTvNow.setSelected(false);
		mTvEnd.setSelected(true);
		mTvAll.setSelected(false);
	}

	@OnClick({R.id.f_votes_tv_all})
	public void onClickAll() {
		mTvNow.setSelected(false);
		mTvEnd.setSelected(false);
		mTvAll.setSelected(true);
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
		pb.setVisibility(active ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setRefreshing(boolean isRefreshing) {
		srlVotes.setRefreshing(isRefreshing);
	}

	@Override
	public void setCanLoadMore(boolean canLoadMore) {
		adapter.setCanLoadMore(canLoadMore);
	}

	@Override
	public void showNoNotices() {

	}

	@Override
	public void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void addVotes(ArrayList<Vote> voteList) {
		adapter.addVotes(voteList);
	}

	@Override
	public void showNotices(ArrayList<Vote> voteList) {
		adapter.setVotes(voteList);
	}

	private boolean needToIgnoreMoreLoadingRequest(LinearLayoutManager layoutManager) {
		return layoutManager == null || !adapter.canLoadMore() || layoutManager.findLastVisibleItemPosition() < adapter.getItemCount() - 1;
	}

	@Override
	public void onRefresh() {
		if (presenter == null) {
			return;
		}

		presenter.refresh();
	}
}
