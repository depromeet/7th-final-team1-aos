package com.depromeet.finddepro.main;

import androidx.test.espresso.idling.CountingIdlingResource;

import com.depromeet.finddepro.data.Notice;
import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EspressoIdlingResource.class, CountingIdlingResource.class})
public class NoticesPresenterTest {

    @Mock
    private NoticesContract.View view;

    @Mock
    private NoticesRepository repository;

    private NoticesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockStatic(CountingIdlingResource.class, EspressoIdlingResource.class);
        presenter = new NoticesPresenter(repository, view);
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;
        view = null;
        repository = null;
    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessful() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = new ArrayList<>();
            notices.add(new Notice(1, "1", "1"));
            notices.add(new Notice(2, "2", "2"));
            notices.add(new Notice(3, "3", "3"));
            notices.add(new Notice(4, "4", "4"));
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyInt(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessful() {

        ArrayList<Notice> notices = new ArrayList<>();

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            notices.add(new Notice(1, "1", "1"));
            notices.add(new Notice(2, "2", "2"));
            notices.add(new Notice(3, "3", "3"));
            notices.add(new Notice(4, "4", "4"));
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyInt(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showNotices(eq(notices));

    }
}