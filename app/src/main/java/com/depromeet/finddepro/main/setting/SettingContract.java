package com.depromeet.finddepro.main.setting;

import com.depromeet.finddepro.data.User;

class SettingContract {
    interface View {
        void showUserProfileInfo(User user);

        void showUserProfileImg(String img);

        void showPushAlarm(boolean isActive);

        void showToast(String msg);

        boolean isActive();
    }

    interface Presenter {
        void start();

        void changeProfileImg(String imgPath);
    }
}