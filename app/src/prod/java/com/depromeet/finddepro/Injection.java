package com.depromeet.finddepro;

import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.data.Repositories;
import com.depromeet.finddepro.data.source.NoticesRemoteDataSource;
import com.depromeet.finddepro.network.FindDeproService;

public class Injection {
    public static NoticesRepository provideUsersRepository() {
        // @TODO : (jonghyo) 실제 콜로 바꿔
        return Repositories.getNoticesRepoInstance(new NoticesRemoteDataSource(FindDeproService.getNoticeApi()));
    }
}
