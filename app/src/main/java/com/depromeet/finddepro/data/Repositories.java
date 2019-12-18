package com.depromeet.finddepro.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.depromeet.finddepro.data.source.NoticesDataSource;

import static androidx.core.util.Preconditions.checkNotNull;

public class Repositories {

    private Repositories() {

    }

    private static NoticesRepository noticesRepository = null;

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

}
