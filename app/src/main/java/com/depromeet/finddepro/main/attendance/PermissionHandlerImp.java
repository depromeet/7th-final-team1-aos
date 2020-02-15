package com.depromeet.finddepro.main.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionHandlerImp implements PermissionsHandler {

    private final Context mContext;


    PermissionHandlerImp(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean checkHasPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(String[] permissions, int requestCode) {

        ActivityCompat.requestPermissions((Activity) mContext, permissions, requestCode);
    }


}
