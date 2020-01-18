package com.depromeet.finddepro.main;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void ShowToast(String text);

    void setText(String text);
}
