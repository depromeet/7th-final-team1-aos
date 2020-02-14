package com.depromeet.finddepro.main.setting;

import com.depromeet.finddepro.data.User;

class SettingContract {
    interface View {
        void showUserProfile(User user);

        void showPushAlarm(boolean isActive);

        boolean isActive();
    }

    interface Presenter {
        void start();
    }
}
