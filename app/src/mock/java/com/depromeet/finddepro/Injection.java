package com.depromeet.finddepro;

import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.data.Repositories;
import com.depromeet.finddepro.data.SchedulesRepository;
import com.depromeet.finddepro.data.source.FakeNoticesDataSource;
import com.depromeet.finddepro.data.source.FakeSchedulesDataSource;

public class Injection {

    public static NoticesRepository provideNoticesRepository() {
        return Repositories.getNoticesRepoInstance(new FakeNoticesDataSource());
    }

    public static SchedulesRepository provideSchedulesRepository() {
        return Repositories.getSchedulesRepoInstance(new FakeSchedulesDataSource());
    }
}
