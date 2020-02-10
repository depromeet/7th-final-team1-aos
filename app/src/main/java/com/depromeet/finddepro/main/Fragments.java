package com.depromeet.finddepro.main;

import com.depromeet.finddepro.main.notice.NoticesFragment;
import com.depromeet.finddepro.main.schedule.ScheduleFragment;

/*
    Fragments : Fragments load

 */
class Fragments {
    private static NoticesFragment noticesFragment = null;
    private static ScheduleFragment scheduleFragment = null;

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


}

