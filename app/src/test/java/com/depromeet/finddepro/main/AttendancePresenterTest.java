package com.depromeet.finddepro.main;


import androidx.test.espresso.idling.CountingIdlingResource;

import com.depromeet.finddepro.data.AttendanceInfo;
import com.depromeet.finddepro.data.AttendanceRepository;
import com.depromeet.finddepro.main.attendance.AttendanceContract;
import com.depromeet.finddepro.main.attendance.AttendancePresenter;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EspressoIdlingResource.class, CountingIdlingResource.class})
public class AttendancePresenterTest {
    @Mock
    private AttendanceContract.View view;

    @Mock
    private AttendanceRepository repository;

    private AttendancePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockStatic(CountingIdlingResource.class, EspressoIdlingResource.class);
        presenter = new AttendancePresenter(repository, view);
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;
        view = null;
        repository = null;
    }


    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndNotNull() {

        //Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceInfo attendanceInfo = new AttendanceInfo(10, 7, 0);
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onSuccess(attendanceInfo);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view).showAttendanceInfo(any());

    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsSuccessfulAndNull() {

        //Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onSuccess(null);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view, never()).showAttendanceInfo(any());
        verify(view).showNoAttendanceInfo();
    }

    @Test
    public void testStartWhenViewIsActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";

        //Given
        when(view.isActive()).thenReturn(true);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view, never()).showAttendanceInfo(any());
        verify(view, never()).showNoAttendanceInfo();
        verify(view).showToast(eq(errorMsg));

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndNotNull() {

        //Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceInfo attendanceInfo = new AttendanceInfo(10, 7, 0);
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onSuccess(attendanceInfo);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view, never()).showAttendanceInfo(any());

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsSuccessfulAndNull() {

        //Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onSuccess(null);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view, never()).showAttendanceInfo(any());
        verify(view, never()).showNoAttendanceInfo();

    }

    @Test
    public void testStartWhenViewIsNotActiveAndResponseIsFailure() {
        String errorMsg = "onFailure";

        //Given
        when(view.isActive()).thenReturn(false);
        doAnswer((Answer<Void>) invocation -> {
            AttendanceRepository.GetAttendanceInfoCallback callback = invocation.getArgumentAt(0, AttendanceRepository.GetAttendanceInfoCallback.class);
            callback.onFailure("", errorMsg);
            return null;
        }).when(repository).getAttendanceInfo(any(AttendanceRepository.GetAttendanceInfoCallback.class));

        //when
        presenter.start();

        //Then
        verify(view, never()).showAttendanceInfo(any());
        verify(view, never()).showNoAttendanceInfo();
        verify(view, never()).showToast(errorMsg);

    }
}
