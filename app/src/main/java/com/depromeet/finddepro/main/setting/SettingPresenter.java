package com.depromeet.finddepro.main.setting;


import android.net.Uri;

import com.depromeet.finddepro.data.User;

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View view;


    SettingPresenter(SettingContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

        //todo : (hee) 테스트 용, 카카오에서 넘어온 이미지일 경우-url
        Uri tmp = Uri.parse("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        view.showUserProfileInfo(new User("johee", "조희", "johee@gmail.com",
                tmp.toString(), false));
        view.showPushAlarm(false);  //알람 여부는 user 정보에 있음

    }

    @Override
    public void changeProfileImg(String imgPath) {
        //서버로 바뀐 이미지정보 넘겨주기
        //응답 받은 정보로 다시 뿌려주기..?
    }

}
