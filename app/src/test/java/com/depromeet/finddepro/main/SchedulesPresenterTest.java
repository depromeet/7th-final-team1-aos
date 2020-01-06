package com.depromeet.finddepro.main;


import androidx.test.espresso.idling.CountingIdlingResource;

import com.depromeet.finddepro.data.Schedule;
import com.depromeet.finddepro.data.SchedulesRepository;
import com.depromeet.finddepro.main.schedule.ScheduleContract;
import com.depromeet.finddepro.main.schedule.SchedulePresenter;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EspressoIdlingResource.class, CountingIdlingResource.class})
public class SchedulesPresenterTest {

    @Mock
    private ScheduleContract.View view;

    @Mock
    private SchedulesRepository repository;

    private SchedulePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockStatic(CountingIdlingResource.class, EspressoIdlingResource.class);
        presenter = new SchedulePresenter(repository, view);
    }


    @After
    public void tearDown() throws Exception {
        presenter = null;
        view = null;
        repository = null;
    }


    //Start Method Test
    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(10);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);
        verify(view, never()).setCanLoadMore(true);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showSchedules(any());


        // @TODO(hee : 주석제거하기) view가 is not active할 때, setLoadingIndicator를 제외하고 나머지는 호출하면 안 됨 (never())


    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);

            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);
        verify(view, never()).setCanLoadMore(false);
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).showNoSchedules();

        // @TODO(hee : 주석제거하기) view가 is not active할 때, setLoadingIndicator를 제외하고 나머지는 호출하면 안 됨 (never()) Empty일 때, showNoSchedules()


    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(10);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);
        verify(view).setCanLoadMore(true);
        verify(view).setLoadingIndicator(false);
        verify(view).showSchedules(any());
    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);
        verify(view).setCanLoadMore(false);
        verify(view).setLoadingIndicator(false);
        verify(view).showNoSchedules();
    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showToast(errorMsg);

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";
        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view).setLoadingIndicator(true);

        verify(view).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, times(1)).showToast(eq(errorMsg));

    }


    //Refresh Method Test
    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(8);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();

        verify(view, never()).setCanLoadMore(false);

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showSchedules(any());


    }

    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();

        verify(view, never()).setCanLoadMore(false);

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showNoSchedules();


    }


    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(8);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();

        verify(view).setCanLoadMore(false);

        verify(view, never()).setLoadingIndicator(false);
        verify(view).setRefreshing(false);
        verify(view).showSchedules(any());
    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();

        verify(view).setCanLoadMore(false);

        verify(view, never()).setLoadingIndicator(false);
        verify(view).setRefreshing(false);
        verify(view).showNoSchedules();
    }

    @Test
    public void testRefreshWhenViewIsNotActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(false);
        String errorMsg = "onFailure";

        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();


        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showToast(eq(errorMsg));

    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";
        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.refresh();

        // Then
        verify(repository).clearCaches();

        verify(view, never()).setLoadingIndicator(false);
        verify(view).setRefreshing(false);
        verify(view).showToast(eq("onFailure"));

    }

    @Test
    public void testRefreshWhenViewIsActiveAndResponseIsSuccessfulAndNotEmptyAndLoadingIsTrue() {
        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(10);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.setIsLoading(true);   //@Todo(hee : 확인해보기)
        presenter.refresh();

        // Then
        verify(repository, never()).clearCaches();
        verify(view, never()).setLoadingIndicator(false);
        verify(view).setRefreshing(false);
        verify(view).showToast("로딩중..잠시 후 다시 시도해주세요.");
    }

    //LoadMore Method Test
    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(10);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setCanLoadMore(true);
        verify(view, never()).addSchedules(any());
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showSchedules(any());
    }

    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setCanLoadMore(true);
        verify(view, never()).addSchedules(any());
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showNoSchedules();


    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsSuccessfulAndNotEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = getDummyScheduleList(10);
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.loadMore();

        // Then
        verify(view).setCanLoadMore(true);
        verify(view).addSchedules(any());

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showSchedules(any());

    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsSuccessfulAndEmpty() {

        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onSuccess(new ArrayList<>());
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.loadMore();

        // Then
        verify(view).setCanLoadMore(false);
        verify(view).addSchedules(any());

        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showNoSchedules();

    }

    @Test
    public void testLoadMoreWhenViewIsNotActiveAndResponseIsFailure() {

        // Given
        when(view.isActive()).thenReturn(false);
        String errorMsg = "onFailure";

        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view, never()).showToast(eq(errorMsg));

    }

    @Test
    public void testLoadMoreWhenViewIsActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";
        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));


        // When
        presenter.loadMore();

        // Then
        verify(view, never()).setLoadingIndicator(false);
        verify(view, never()).setRefreshing(false);
        verify(view).showToast(eq("onFailure"));

    }
    private ArrayList<Schedule> getDummyScheduleList(int cnt) {
        ArrayList<Schedule> dummyScheduleList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            dummyScheduleList.add(new Schedule(i, 1577789181000L + i, "일정입니다."));
        }

        return dummyScheduleList;
    }

}
