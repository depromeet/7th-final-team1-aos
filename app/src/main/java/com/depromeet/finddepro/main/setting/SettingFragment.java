package com.depromeet.finddepro.main.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingFragment extends Fragment implements SettingContract.View {

    private final SettingContract.Presenter presenter;
    @BindView(R.id.f_setting_img_profile)
    ImageView profile;
    @BindView(R.id.f_setting_tv_name)
    TextView name;
    @BindView(R.id.f_setting_tv_email)
    TextView email;
    @BindView(R.id.f_setting_like_btn)
    ImageView likeInfoBtn;
    @BindView(R.id.f_setting_programInfo_btn)
    ImageView programInfoBtn;
    @BindView(R.id.f_setting_switch_pushAlarm)
    Switch pushAlarm;

    private Unbinder unbinder;

    public SettingFragment() {
        presenter = new SettingPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.start();
    }


    @Override
    public void showUserProfile(User user) {
        name.setText(user.getName());
        email.setText(user.getEmail());
        Glide.with(this).load(user.getProfileUrl()).apply(new RequestOptions().circleCrop()).into(profile);

    }

    @Override
    public void showPushAlarm(boolean isActive) {
        if (isActive) {
            pushAlarm.setChecked(true);
        } else {
            pushAlarm.setChecked(false);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
