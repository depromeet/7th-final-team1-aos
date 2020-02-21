package com.depromeet.finddepro.main.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

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

    private static final int GET_GALLERY_IMAGE = 0;

    private Unbinder unbinder;

    public SettingFragment() {
        presenter = new SettingPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, root);

        profile.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, GET_GALLERY_IMAGE);
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_GALLERY_IMAGE) {
            if (resultCode == RESULT_OK) {

                String imgPath = data.getData().toString();
                showUserProfileImg(imgPath);
                presenter.changeProfileImg(imgPath);

            } else if (resultCode == RESULT_CANCELED) {
                //showToast("사진 선택 취소");
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.start();
    }

    @Override
    public void showUserProfileInfo(User user) {
        name.setText(user.getName());
        email.setText(user.getEmail());
        showUserProfileImg(user.getProfileUrl());
    }

    @Override
    public void showUserProfileImg(String img) {
        //TODO hee: GlideApp으로 바꾸기 + 글라이드 밖으로 안 보이게
        Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(profile);
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
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
