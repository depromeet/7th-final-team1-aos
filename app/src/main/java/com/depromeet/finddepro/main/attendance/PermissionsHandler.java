package com.depromeet.finddepro.main.attendance;

public interface PermissionsHandler {
    boolean checkHasPermission(String permission);

    void requestPermission(String[] permissions, int requestCode);

}
