package com.depromeet.finddepro.data;

public class AttendanceInfo {
    private int total;
    private int attendance;
    private int absence;
    private transient int remainingAttendance;

    public AttendanceInfo(int total, int attendance, int absence) {
        this.total = total;
        this.attendance = attendance;
        this.absence = absence;
        this.remainingAttendance = total - attendance - absence;
    }

    public int getTotal() {
        return total;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getAbsence() {
        return absence;
    }

    public int getRemainingAttendance() {
        return remainingAttendance;
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
        return String.valueOf(remainingAttendance);
    }

}
