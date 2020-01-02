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

    // Start Method Test
    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = getDummyNoticeList(10);
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).setLoadingIndicator(true);

        verify(view, never()).setCanLoadMore(true);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(getDummyNoticeList(10));
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).setLoadingIndicator(true);

        verify(view, times(1)).setCanLoadMore(true);
        verify(view, times(1)).setLoadingIndicator(false);
        verify(view, times(1)).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).setLoadingIndicator(true);

        verify(view, never()).setCanLoadMore(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).setCanLoadMore(false);
        verify(view, times(1)).setLoadingIndicator(true);
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
        verify(view, times(1)).setLoadingIndicator(true);

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showNotices(any());
        verify(view, never()).showToast(eq("error"));

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsFailure() {

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
        verify(view, times(1)).setLoadingIndicator(true);

        verify(view, times(1)).setLoadingIndicator(false);
        verify(view, never()).showNotices(any());
        verify(view, times(1)).showToast(eq("error"));

    }

    // Refresh Method Test
    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(getDummyNoticeList(10));
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showNotices(any());

    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(getDummyNoticeList(10));
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).setRefreshing(false);
        verify(view, times(1)).showNotices(any());

    }

    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = new ArrayList<>();
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showNoNotices();

    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).setRefreshing(false);
        verify(view, times(1)).showNoNotices();

    }

    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showToast(eq("error"));

    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository, times(1)).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).setRefreshing(false);
        verify(view, times(1)).showToast(eq("error"));

    }

    // loadMore Method Test
    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(getDummyNoticeList(10));
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setCanLoadMore(true);
        verify(view, never()).addNotices(any());

    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            ArrayList<Notice> notices = getDummyNoticeList(10);
            callback.onSuccess(notices);
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).setCanLoadMore(true);
        verify(view, times(1)).addNotices(any());

    }

    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setCanLoadMore(false);
        verify(view, never()).addNotices(any());

    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).setCanLoadMore(false);
        verify(view, times(1)).addNotices(any());

    }

    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showToast(eq("error"));

    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            NoticesRepository.GetNoticeListCallback callback = invocation.getArgumentAt(1, NoticesRepository.GetNoticeListCallback.class);
            callback.onFailure("9999", "error");
            return null;
        }).when(repository).getNoticeList(anyBoolean(), any(NoticesRepository.GetNoticeListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setRefreshing(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, times(1)).showToast(eq("error"));

    }

    private ArrayList<Notice> getDummyNoticeList(int cnt) {
        ArrayList<Notice> dummyNoticeList = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            dummyNoticeList.add(new Notice(i, String.valueOf(i), String.valueOf(i), 1577327181000L + i));
        }

        return dummyNoticeList;
    }

}