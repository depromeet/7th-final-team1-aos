package com.depromeet.finddepro.main.attendance;

import com.depromeet.finddepro.data.AttendanceInfo;

public interface AttendanceInfoContract {
    interface View {

        void showNoAttendanceInfo();

        void showAttendanceInfo(AttendanceInfo attendanceInfo);

        void clearAttendanceInfo();

        void showToast(String msg);

        boolean isActive();
    }

    interface Presenter {
        void start();

        void update();  //출석 체크 한 후 update
    }
}
