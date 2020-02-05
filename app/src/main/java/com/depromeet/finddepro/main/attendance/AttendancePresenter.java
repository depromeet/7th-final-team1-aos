package com.depromeet.finddepro.main.attendance;

import android.Manifest;

import com.depromeet.finddepro.data.AttendanceInfo;
import com.depromeet.finddepro.data.AttendanceRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

public class AttendancePresenter implements AttendanceContract.Presenter {
    //@todo(hee:상수 나중에 빼기)
    static final int GPS_ENABLE_REQUEST_CODE = 2001;
    static final int PERMISSIONS_REQUEST_CODE = 100;
    static String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private final AttendanceRepository repository;
    private final AttendanceContract.View view;
    private double latitude, longitude;
    private PermissionsHandler permissionsHandler;
    private LocationHandler locationHandler;

    public AttendancePresenter(AttendanceRepository repository, AttendanceContract.View view) {
        this.repository = repository;
        this.view = view;

    }

    @Override
    public void start() {
        EspressoIdlingResource.increment();
        loadAttendanceInfo();
    }

    @Override
    public void update() {
        EspressoIdlingResource.increment();
        repository.clearCaches();
        loadAttendanceInfo();
    }

    private void loadAttendanceInfo() {
        repository.getAttendanceInfo(new AttendanceRepository.GetAttendanceInfoCallback() {
            @Override
            public void onSuccess(AttendanceInfo attendanceInfo) {
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;
                if (attendanceInfo == null)
                    view.showNoAttendanceInfo();
                else {
                    if (checkRemaining(attendanceInfo))
                        view.showAttendanceInfo(attendanceInfo);
                    else
                        view.showNoAttendanceInfo();
                }
            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;

                view.showToast(msg);


            }
        });
    }

    private boolean checkRemaining(AttendanceInfo attendanceInfo) {
        return attendanceInfo.getRemainingAttendance() >= 0;
    }

    @Override
    public void checkAttendance(String id) {
        EspressoIdlingResource.increment();

        setLocationInfo();

        repository.getCheckedAttendanceResult(latitude, longitude, id, new AttendanceRepository.GetCheckedAttendanceCallback() {
            @Override
            public void onSuccess(String result) {
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;
                //@todo(hee: result 수정하기)
                if (!result.equals("checked")) {
                    view.showToast("다시확인해주세요.");

                    EspressoIdlingResource.increment();
                    view.startQRScan();
                } else {
                    view.showToast(result);
                    update();
                }

            }

            @Override
            public void onFailure(String code, String msg) {
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;

                view.showToast(msg);

            }
        });
    }

    private void setLocationInfo() {
        latitude = locationHandler.getLatitude();
        longitude = locationHandler.getLongitude();
    }

    @Override
    public void requestQRScan() {
        setLocationInfo();

        if (latitude != 0.0 && longitude != 0.0)  //@todo (hee : if 조건 바꾸기)
            view.startQRScan();
    }

    @Override
    public void refresh() {
        EspressoIdlingResource.increment();
        repository.clearCaches();
    }

    @Override
    public void initLocationServicesAndPermission() {
        permissionsHandler = new PermissionHandlerImp(view.getViewActivity());
        locationHandler = new LocationHandlerImp(view.getViewActivity());


        if (!checkLocationServicesStatus())
            view.showDialogForLocationServiceSetting();
        else {
            if (!checkLocationPermission())
                permissionsHandler.requestPermission(REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
        }

    }


    public boolean checkLocationServicesStatus() {
        return locationHandler.checkLocationServicesStatus();
    }

    public boolean checkLocationPermission() {
        boolean check = false;
        if (permissionsHandler.checkHasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                && permissionsHandler.checkHasPermission(Manifest.permission.ACCESS_COARSE_LOCATION))
            check = true;
        return check;

    }

}
