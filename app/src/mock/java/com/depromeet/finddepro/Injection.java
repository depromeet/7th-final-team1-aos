package com.depromeet.finddepro;

import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.data.Repositories;
import com.depromeet.finddepro.data.SchedulesRepository;
import com.depromeet.finddepro.data.source.FakeNoticesDataSource;
import com.depromeet.finddepro.data.source.FakeSchedulesDataSource;

public class Injection {

    public static NoticesRepository provideUsersRepository() {
        return Repositories.getNoticesRepoInstance(new FakeNoticesDataSource());
    }

    //뭔지 잘 모르겠어요..ㅠㅅㅠ
    public static SchedulesRepository provideUsersRepository2() {
        return Repositories.getSchedulesRepoInstance(new FakeSchedulesDataSource());
    }
}
