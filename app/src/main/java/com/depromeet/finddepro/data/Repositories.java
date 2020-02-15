package com.depromeet.finddepro.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.depromeet.finddepro.data.source.AttendanceDataSource;
import com.depromeet.finddepro.data.source.NoticesDataSource;
import com.depromeet.finddepro.data.source.SchedulesDataSource;

import static androidx.core.util.Preconditions.checkNotNull;

public class Repositories {

    private Repositories() {

    }

    private static NoticesRepository noticesRepository = null;
    private static SchedulesRepository schedulesRepository = null;
    private static AttendanceRepository attendanceRepository = null;

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


    public static AttendanceRepository getAttendanceInfoInstance(@NonNull AttendanceDataSource attendanceInfoDataSource) {
        checkNotNull(attendanceInfoDataSource);

        if (attendanceRepository == null) {
            attendanceRepository = new InMemoryAttendanceRepository(attendanceInfoDataSource);
        }
        return attendanceRepository;
    }


    @VisibleForTesting
    public static AttendanceRepository getAttendanceInfoInstance() {
        return attendanceRepository;
    }

}
