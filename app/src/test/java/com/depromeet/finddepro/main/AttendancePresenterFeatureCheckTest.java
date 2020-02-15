package com.depromeet.finddepro.main;

import androidx.test.espresso.idling.CountingIdlingResource;

import com.depromeet.finddepro.data.AttendanceRepository;
import com.depromeet.finddepro.main.attendance.AttendanceContract;
import com.depromeet.finddepro.main.attendance.AttendancePresenter;
import com.depromeet.finddepro.main.attendance.LocationHandler;
import com.depromeet.finddepro.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest({EspressoIdlingResource.class, CountingIdlingResource.class})
public class AttendancePresenterFeatureCheckTest {
    @Mock
    private AttendanceContract.View view;

    @Mock
    private AttendanceRepository repository;

    @Mock
    private LocationHandler locationHandler;


    private AttendancePresenter presenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockStatic(CountingIdlingResource.class, EspressoIdlingResource.class);
        presenter = new AttendancePresenter(repository, view);

    }

    @After
    public void tearDown() {
        presenter = null;
        view = null;
        repository = null;
        locationHandler = null;
    }
    // checkAttendance Test

    @Test
    public void testCheckAttendanceWhenViewIsActiveAndResponseIsSuccessful() {
//
//        String result = "checked";
//        //Given
//        when(locationHandler.getLongitude()).thenReturn(2.0);
//        when(locationHandler.getLatitude()).thenReturn(2.0);
//
//        double lat = locationHandler.getLatitude();
//        double lon = locationHandler.getLongitude();
//
//        System.out.println("lat: "+lat+" lon:"+lon);
//        when(view.isActive()).thenReturn(true);
//        doAnswer((Answer<Void>) invocation -> {
//            AttendanceRepository.GetCheckedAttendanceCallback callback = invocation.getArgumentAt(3, AttendanceRepository.GetCheckedAttendanceCallback.class);
//            callback.onSuccess(result);
//
//            return null;
//        }).when(repository).getCheckedAttendanceResult(
//                anyDouble(),
//                anyDouble(),
//                anyString(),
//                any(AttendanceRepository.GetCheckedAttendanceCallback.class));
//
//        //when
//        presenter.checkAttendance("id");
//
//        //Then
//        verify(view).showToast(eq("checked"));

    }

}
