package com.depromeet.finddepro.main.attendance;

import android.app.Activity;

import com.depromeet.finddepro.data.AttendanceInfo;

public interface AttendanceContract {
    interface View {

        void showNoAttendanceInfo();

        void showAttendanceInfo(AttendanceInfo attendanceInfo);

        void clearAttendanceInfo();

        void showToast(String msg);

        boolean isActive();

        void startQRScan();     //QR Code Scan하는 카메라 뷰로 전환


        void showDialogForLocationServiceSetting();

        Activity getViewActivity();
    }

    interface Presenter {
        void start();

        void update();  //출석 체크 한 후 update

        void checkAttendance(String data); //출석체크 @todo(hee: data는 qr code에서 얻은 정보(추후에 바꿀 예정))

        void requestQRScan();

        void refresh();

        boolean checkLocationServicesStatus();

        boolean checkLocationPermission();

        void initLocationServicesAndPermission();


    }

}
