package com.depromeet.finddepro.main.attendance;


public interface LocationHandler {
    boolean checkLocationServicesStatus();

    double getLatitude();

    double getLongitude();
}
