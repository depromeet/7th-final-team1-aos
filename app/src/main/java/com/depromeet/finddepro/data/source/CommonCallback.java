package com.depromeet.finddepro.data.source;

interface CommonCallback {
    void onSuccess();

    void onFailure(String code, String message);
}
