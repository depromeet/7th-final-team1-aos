package com.depromeet.finddepro.main.setting;


import com.depromeet.finddepro.data.User;

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View view;

    SettingPresenter(SettingContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

        //todo : (hee) 테스트 용, 추후에 user 구현
        view.showUserProfileInfo(new User("user id", "user name", "user@gmail.com",
                "", false));
        view.showPushAlarm(false);  //알람 여부는 user 정보에 있음

    }

    @Override
    public void changeProfileImg(String imgPath) {
        //서버로 바뀐 이미지정보 넘겨주기
        //응답 받은 정보로 다시 뿌려주기
    }

}
