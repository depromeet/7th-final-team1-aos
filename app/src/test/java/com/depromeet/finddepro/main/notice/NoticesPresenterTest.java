package com.depromeet.finddepro.main.notice;

import androidx.test.espresso.idling.CountingIdlingResource;

import com.depromeet.finddepro.data.Notice;
import com.depromeet.finddepro.data.NoticesRepository;
import com.depromeet.finddepro.main.notice.NoticesContract;
import com.depromeet.finddepro.main.notice.NoticesPresenter;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
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
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = new ArrayList<>();
            notices.add(new Notice(1, "1", "1", 1577327181000L));
            notices.add(new Notice(2, "2", "2", 1577327181001L));
            notices.add(new Notice(3, "3", "3", 1577327181002L));
            notices.add(new Notice(4, "4", "4", 1577327181003L));
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        ArrayList<Notice> notices = new ArrayList<>();

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            notices.add(new Notice(1, "1", "1", 1577327181000L));
            notices.add(new Notice(2, "2", "2", 1577327181001L));
            notices.add(new Notice(3, "3", "3", 1577327181002L));
            notices.add(new Notice(4, "4", "4", 1577327181003L));
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showNotices(eq(notices));

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = new ArrayList<>();
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        ArrayList<Notice> notices = new ArrayList<>();

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showNoNotices();

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showNotices(any());
        verify(view, never()).showToast(anyString());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsFailure() {

        ArrayList<Notice> notices = new ArrayList<>();

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showNotices(any());
        verify(view, times(1)).showToast(anyString());

    }


}