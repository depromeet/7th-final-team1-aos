package com.depromeet.finddepro.main;

import androidx.fragment.app.Fragment;

import com.depromeet.finddepro.main.attendance.AttendanceFragment;
import com.depromeet.finddepro.main.notice.NoticesFragment;
import com.depromeet.finddepro.main.schedule.ScheduleFragment;
import com.depromeet.finddepro.main.vote.VotesFragment;

/*
    Fragments : Fragments load

 */
class Fragments {
    private static NoticesFragment noticesFragment = null;
    private static ScheduleFragment scheduleFragment = null;
    private static AttendanceFragment attendanceFragment = null;
    private static VotesFragment votesFragment = null;

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

    static AttendanceFragment getAttendanceFragment() {
        if (attendanceFragment == null)
            attendanceFragment = new AttendanceFragment();

        return attendanceFragment;
    }

    static Fragment getVotesFragment() {
        if (votesFragment == null)
            votesFragment = new VotesFragment();

        return votesFragment;
    }
}