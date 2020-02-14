package com.depromeet.finddepro.main;

import com.depromeet.finddepro.main.notice.NoticesFragment;
import com.depromeet.finddepro.main.schedule.ScheduleFragment;
import com.depromeet.finddepro.main.setting.SettingFragment;

/*
    Fragments : Fragments load

 */
class Fragments {
    private static NoticesFragment noticesFragment = null;
    private static ScheduleFragment scheduleFragment = null;
    private static SettingFragment settingFragment = null;

    private Fragments() {

    }

    static NoticesFragment getNoticesFragment() {
        if (noticesFragment == null)
            noticesFragment = new NoticesFragment();

        return noticesFragment;
    }

    static ScheduleFragment getScheduleFragment() {
        if (scheduleFragment == null)
            scheduleFragment = new ScheduleFragment();

        return scheduleFragment;
    }

    static SettingFragment getSettingFragment() {
        if (settingFragment == null)
            settingFragment = new SettingFragment();

        return settingFragment;
    }

}

