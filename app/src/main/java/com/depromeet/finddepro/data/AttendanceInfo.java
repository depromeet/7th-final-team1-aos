package com.depromeet.finddepro.data;

public class AttendanceInfo {
    private int total;
    private int attendance;
    private int absence;

    public AttendanceInfo(int total, int attendance, int absence) {
        this.total = total;
        this.attendance = attendance;
        this.absence = absence;
    }

    public String getTotalStr() {
        return String.valueOf(total);
    }

    public String getAttendanceStr() {
        return String.valueOf(attendance);
    }

    public String getAbsenceStr() {
        return String.valueOf(absence);
    }

    public String getRemainingAttendanceStr() {
        int remaining = total - attendance - absence;
        return String.valueOf(remaining);
    }
}
