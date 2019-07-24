package com.example.chatrealtimetracking.splash;


import com.example.chatrealtimetracking.base.BaseView;

public interface SplashContract {

    interface Presenter {
        void runSplash(long timer);
        boolean isCheckSession();
    }

    interface View extends BaseView {
        void isSuccess();
        void isFailed();
    }
}
