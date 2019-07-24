package com.example.chatrealtimetracking.splash;

import android.content.SharedPreferences;
import android.os.Handler;
import com.example.chatrealtimetracking.base.BasePresenter;
import com.example.chatrealtimetracking.utils.SessionManager;


public class SplashPresenter implements BasePresenter<SplashContract.View>, SplashContract.Presenter {

    SplashContract.View splashView;
    SharedPreferences preferences;

    public SplashPresenter(SplashContract.View splashView, SharedPreferences preferences) {
        this.splashView = splashView;
        this.preferences = preferences;
    }

    @Override
    public void onAttach(SplashContract.View view) {
        splashView = view;
    }

    @Override
    public void onDettach() {
        splashView = null;
    }

    @Override
    public void runSplash(long timer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isCheckSession()) {
                    splashView.isSuccess();

                } else {
                    splashView.isFailed();
                }
            }
        }, timer);
    }

    @Override
    public boolean isCheckSession() {
        return preferences.getBoolean(SessionManager.IS_LOGIN, false);
    }
}
