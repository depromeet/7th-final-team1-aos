package com.depromeet.finddepro.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.depromeet.finddepro.data.source.AttendanceInfoDataSource;
import com.depromeet.finddepro.data.source.NoticesDataSource;
import com.depromeet.finddepro.data.source.SchedulesDataSource;

import static androidx.core.util.Preconditions.checkNotNull;

public class Repositories {

    private Repositories() {

    }

    private static NoticesRepository noticesRepository = null;
    private static SchedulesRepository schedulesRepository = null;
    private static AttendanceInfoRepository attendanceInfoRepository = null;


    public static NoticesRepository getNoticesRepoInstance(@NonNull NoticesDataSource noticesDataSource) {
        checkNotNull(noticesDataSource);

        if (noticesRepository == null) {
            noticesRepository = new InMemoryNoticesRepository(noticesDataSource);
        }

        return noticesRepository;
    }

    @VisibleForTesting
    public static NoticesRepository getNoticesRepoInstance() {
        return noticesRepository;
    }


    public static SchedulesRepository getSchedulesRepoInstance(@NonNull SchedulesDataSource schedulesDataSource) {
        checkNotNull(schedulesDataSource);

        if (schedulesRepository == null) {
            schedulesRepository = new InMemorySchedulesRepository(schedulesDataSource);
        }
        return schedulesRepository;
    }

    @VisibleForTesting
    public static SchedulesRepository getSchedulesRepoInstance() {
        return schedulesRepository;
    }


    public static AttendanceInfoRepository getAttendanceInfoInstance(@NonNull AttendanceInfoDataSource attendanceInfoDataSource) {
        checkNotNull(attendanceInfoDataSource);

        if (attendanceInfoRepository == null) {
            attendanceInfoRepository = new InMemoryAttendanceInfoRepository(attendanceInfoDataSource);
        }
        return attendanceInfoRepository;
    }


    @VisibleForTesting
    public static AttendanceInfoRepository getAttendanceInfoInstance() {
        return attendanceInfoRepository;
    }
}
