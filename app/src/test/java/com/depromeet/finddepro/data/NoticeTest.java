package com.depromeet.finddepro.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoticeTest {

    @Test
    public void getCreateAtStr() {

        // Given
        Notice notice = new Notice(1, "1", "1", 1577327181000L);

        // When
        String result = notice.getCreatedAtStr();

        // Then
        assertEquals("2019/12/26", result);

    }
}