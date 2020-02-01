package com.depromeet.finddepro.main.vote.custom;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.Choice;

public class ChoiceView extends FrameLayout {

	private TextView mTvName;
	private TextView mTvPersonCnt;
	private ImageView mIvChecked;
	private LinearLayout mLlPersonInfoContainer;

	public ChoiceView(Context context) {
		super(context);
		initViews();
	}

	public ChoiceView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initViews();
	}

	public ChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public ChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initViews();
	}

	private void initViews() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_choice, this, true);
		mTvName = view.findViewById(R.id.layout_choice_tv_name);
		mTvPersonCnt = view.findViewById(R.id.layout_choice_tv_person_cnt);
		mIvChecked = view.findViewById(R.id.layout_choice_cb_checked);
		mLlPersonInfoContainer = view.findViewById(R.id.layout_choice_ll_person_info);
	}

	public void bind(Choice choice, ChoiceListener listener) {
		mTvName.setText(choice.getName());
		mTvPersonCnt.setText(choice.getPersonCntStr());
		// @TODO : (jonghyo) 체크 이미지 변경하도록 할 것

		mLlPersonInfoContainer.setOnClickListener(view -> listener.onClickPerson(choice));
		setOnClickListener(view -> listener.onChangedChoice(choice));
	}

	public interface ChoiceListener {

		void onChangedChoice(Choice choice);

		void onClickPerson(Choice choice);

	}
}
