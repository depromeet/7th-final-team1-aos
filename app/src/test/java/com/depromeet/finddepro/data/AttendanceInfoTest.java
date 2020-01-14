package com.depromeet.finddepro.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AttendanceInfoTest {
    @Test
    public void getRemainingAttendanceStr() {
        // Given
        AttendanceInfo attendanceInfo = new AttendanceInfo(10, 8, 0);

        // When
        String result = attendanceInfo.getRemainingAttendanceStr();

        // Then
        assertEquals("2", result);
    }
}
