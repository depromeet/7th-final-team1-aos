package com.depromeet.finddepro.main;

import com.depromeet.finddepro.main.attendance.AttendanceFragment;
import com.depromeet.finddepro.main.notice.NoticesFragment;
import com.depromeet.finddepro.main.schedule.ScheduleFragment;
import com.depromeet.finddepro.main.vote.VotesFragment;
import com.depromeet.finddepro.main.setting.SettingFragment;

/*
    Fragments : Fragments load

 */
public class Fragments {
    private static NoticesFragment noticesFragment = null;
    private static ScheduleFragment scheduleFragment = null;
    private static AttendanceFragment attendanceFragment = null;
    private static VotesFragment votesFragment = null;
    private static SettingFragment settingFragment = null;

    private Fragments() {

    }

    public static NoticesFragment getNoticesFragment() {
        if (noticesFragment == null)
            noticesFragment = new NoticesFragment();

        return noticesFragment;
    }

    public static ScheduleFragment getScheduleFragment() {
        if (scheduleFragment == null)
            scheduleFragment = new ScheduleFragment();

        return scheduleFragment;
    }

    static AttendanceFragment getAttendanceFragment() {
        if (attendanceFragment == null)
            attendanceFragment = new AttendanceFragment();
        return attendanceFragment;
    }

    static VotesFragment getVotesFragment() {
        if (votesFragment == null)
            votesFragment = new VotesFragment();

        return votesFragment;
    }
    static SettingFragment getSettingFragment() {
        if (settingFragment == null)
            settingFragment = new SettingFragment();

        return settingFragment;
    }

}