package com.depromeet.finddepro.main.vote.custom;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.depromeet.finddepro.data.Choice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceContainerView extends LinearLayout {

	private List<Choice> mChoiceList;
	private Map<String, ChoiceView> mViewMap;
	private OnClickEventListener mListener;

	public ChoiceContainerView(Context context) {
		super(context);
		init();
	}

	public ChoiceContainerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ChoiceContainerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public ChoiceContainerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		setOrientation(LinearLayout.VERTICAL);
		this.mViewMap = new HashMap<>();
	}

	public void setChoiceList(List<Choice> choiceList) {
		removeAllViews();
		this.mViewMap.clear();
		this.mChoiceList = choiceList;
		createChoiceViews();
	}

	private void createChoiceViews() {
		for (Choice choice : mChoiceList) {
			ChoiceView choiceView = generateChoiceView(choice);
			mViewMap.put(choice.getId(), choiceView);
			addView(choiceView);
		}
	}

	private ChoiceView generateChoiceView(Choice choice) {
		ChoiceView choiceView = new ChoiceView(getContext());
		choiceView.bind(choice, new ChoiceView.ChoiceListener() {
			@Override
			public void onChangedChoice(Choice choice) {
				if (mListener == null) {
					return;
				}

				mListener.onChangedChoice(choice);
			}

			@Override
			public void onClickPerson(Choice choice) {
				if (mListener == null) {
					return;
				}

				mListener.onClickPerson(choice);
			}
		});
		return choiceView;
	}

	public void setListener(OnClickEventListener listener) {
		this.mListener = listener;
	}

	public interface OnClickEventListener {

		void onChangedChoice(Choice choice);

		void onClickPerson(Choice choice);

	}

}
