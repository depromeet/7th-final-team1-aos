package com.depromeet.finddepro.main.attendance;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.AttendanceInfo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.depromeet.finddepro.main.attendance.AttendancePresenter.GPS_ENABLE_REQUEST_CODE;
import static com.depromeet.finddepro.main.attendance.AttendancePresenter.PERMISSIONS_REQUEST_CODE;
import static com.depromeet.finddepro.main.attendance.AttendancePresenter.REQUIRED_PERMISSIONS;


public class AttendanceFragment extends Fragment implements AttendanceContract.View {
    private final AttendanceContract.Presenter presenter;
    @BindView(R.id.totalAttendance)
    TextView totalAttendance;
    @BindView(R.id.attendance)
    TextView attendance;
    @BindView(R.id.absence)
    TextView absence;
    @BindView(R.id.remaining)
    TextView remaining;
    @BindView(R.id.checkAttendanceBtn)
    TextView checkAttendanceBtn;
    private Unbinder unbinder;


    public AttendanceFragment() {
        presenter = new AttendancePresenter(Injection.provideAttendanceInfoRepository(), this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_attendanceinfo, container, false);
        unbinder = ButterKnife.bind(this, root);


        checkAttendanceBtn.setOnClickListener(v -> {
            presenter.initLocationServicesAndPermission(); // 위치정보를 사용하기 전 세팅
            presenter.requestQRScan();
        });

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.start();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder == null) {
            return;
        }

        unbinder.unbind();
    }


    @Override
    public void showNoAttendanceInfo() {

    }

    @Override
    public void showAttendanceInfo(AttendanceInfo attendanceInfo) {

        totalAttendance.setText(attendanceInfo.getTotalStr());
        attendance.setText(attendanceInfo.getAttendanceStr());
        absence.setText(attendanceInfo.getAbsenceStr());
        remaining.setText(attendanceInfo.getRemainingAttendanceStr());
    }

    @Override
    public void clearAttendanceInfo() {
        totalAttendance.setText("");
        attendance.setText("");
        absence.setText("");
        remaining.setText("");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void startQRScan() {
        new IntentIntegrator(getActivity()).forSupportFragment(AttendanceFragment.this).initiateScan();
    }


    //GPS사용을 위한 관련 메소드

    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE
                && grandResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크
            if (!presenter.checkLocationPermission())
                check_result = false;

            if (!check_result) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {
                    //@todo ( hee: 상의 후 수정하기)
                    showToast("퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.");

                } else {
                    showToast("퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ");

                }
            }

        }
    }


    //@todo(hee: 상의 후 수정, GPS 활성화를 위한 알림창)
    @Override
    public void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", (dialog, id) -> {
            Intent callGPSSettingIntent
                    = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
        });
        builder.setNegativeButton("취소", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (presenter.checkLocationServicesStatus() && presenter.checkLocationPermission())
                    return;
                break;
            default:
                //QR CODE 인식 후
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() == null) {
                        // 확인용 toast
                        //  showToast("Cancelled");
                    } else {

                        // @todo ( hee : 서버에 위치정보랑 QR정보 보내고 처리하기)
                        presenter.checkAttendance(result.getContents());

                    }
                }

                break;
        }


    }

    @Override
    public Activity getViewActivity() {
        return getActivity();
    }

}
