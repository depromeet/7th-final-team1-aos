package com.depromeet.finddepro.main.setting;

import com.depromeet.finddepro.data.User;

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View view;
    private final boolean pushAlarmActive;

    SettingPresenter(SettingContract.View view) {
        this.view = view;
        pushAlarmActive = false;
    }

    @Override
    public void start() {

        //todo : (hee) 테스트 용
        view.showUserProfile(new User("johee", "조희", "johee@gmail.com",
                "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"));
        view.showPushAlarm(pushAlarmActive);

    }
}
