package com.example.chatrealtimetracking.base;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T view);

    void onDettach();

}
