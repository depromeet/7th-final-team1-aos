package com.depromeet.finddepro.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScheduleTest {
    @Test
    public void getDateStr() {
        //Given
        Schedule s = new Schedule(1, 1577789181000L, "일정 내용입니다.");

        //When
        String result = s.getDateStr();
        //Then
        assertEquals("2019/12/31", result);
    }

    @Test
    public void getWeeksStr() {
        //Given
        Schedule s = new Schedule(1, 1577789181000L, "일정 내용입니다.");

        //When
        String result = s.getWeeksStr();
        //Then
        assertEquals("12월 5번째 주", result);
    }
}
