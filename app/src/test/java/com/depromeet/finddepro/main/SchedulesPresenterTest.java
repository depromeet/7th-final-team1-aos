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


    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessful() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            ArrayList<Schedule> schedules = new ArrayList<>();
            schedules.add(new Schedule(1, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(2, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(3, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(4, 1577789181000L, "일정입니다."));
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showSchedules(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessful() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        // Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            schedules.add(new Schedule(1, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(2, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(3, 1577789181000L, "일정입니다."));
            schedules.add(new Schedule(4, 1577789181000L, "일정입니다."));
            callback.onSuccess(schedules);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSchedules(eq(schedules));

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsNotSuccessful() {

        // Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            String errorMsg = "onFailure";
            SchedulesRepository.GetScheduleListCallback callback = invocation.getArgumentAt(1, SchedulesRepository.GetScheduleListCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getScheduleList(anyBoolean(), any(SchedulesRepository.GetScheduleListCallback.class));

        // When
        presenter.start();

        // Then
        verify(view, never()).showToast(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsNotSuccessful() {
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
        verify(view, times(1)).showToast(eq(errorMsg));

    }

}
