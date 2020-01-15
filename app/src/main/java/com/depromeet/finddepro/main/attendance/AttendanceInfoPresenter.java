package com.depromeet.finddepro.main.attendance;

import com.depromeet.finddepro.data.AttendanceInfo;
import com.depromeet.finddepro.data.AttendanceInfoRepository;
import com.depromeet.finddepro.util.EspressoIdlingResource;

public class AttendanceInfoPresenter implements AttendanceInfoContract.Presenter {
    private final AttendanceInfoRepository repository;
    private final AttendanceInfoContract.View view;


    public AttendanceInfoPresenter(AttendanceInfoRepository repository, AttendanceInfoContract.View view) {
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

    public void loadAttendanceInfo() {
        repository.getAttendanceInfo(new AttendanceInfoRepository.GetAttendanceInfoCallback() {
            @Override
            public void onSuccess(AttendanceInfo attendanceInfo) {
                EspressoIdlingResource.decrement();
                if (!view.isActive())
                    return;
                if (attendanceInfo == null)
                    view.showNoAttendanceInfo();
                else {
                    if (checkRemaining(attendanceInfo))  //@todo(hee: remaining 출석일이 음수일 경우가 있을가?)
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
        if (attendanceInfo.getRemainingAttendance() < 0)
            return false;
        return true;
    }
}
