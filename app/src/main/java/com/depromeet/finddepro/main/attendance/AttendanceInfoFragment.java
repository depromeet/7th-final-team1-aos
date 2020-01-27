package com.depromeet.finddepro.main.attendance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.depromeet.finddepro.Injection;
import com.depromeet.finddepro.R;
import com.depromeet.finddepro.data.AttendanceInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AttendanceInfoFragment extends Fragment implements AttendanceInfoContract.View {
    private final AttendanceInfoContract.Presenter presenter;
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

    public AttendanceInfoFragment() {

        presenter = new AttendanceInfoPresenter(Injection.provideAttendanceInfoRepository(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_attendanceinfo, container, false);
        unbinder = ButterKnife.bind(this, root);

        checkAttendanceBtn.setOnClickListener(v -> {
            //intent로 출석체크하는 곳으로 전환
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

}
