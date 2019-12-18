package com.depromeet.finddepro;

import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.data.Repositories;
import com.depromeet.finddepro.data.source.FakeNoticesDataSource;

public class Injection {

    public static NoticesRepository provideUsersRepository() {
        return Repositories.getNoticesRepoInstance(new FakeNoticesDataSource());
    }

}
