package com.example.chatrealtimetracking.splash;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chatrealtimetracking.Main2Activity;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.signIn.SignIn;
import com.example.chatrealtimetracking.utils.MyFunction;
import com.example.chatrealtimetracking.utils.SessionManager;

public class Splash extends AppCompatActivity implements SplashContract.View {

    SessionManager manager;
   SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        manager = new SessionManager(this);
        initPresenter();
        presenter.runSplash(1000);
    }

    private void initPresenter() {
        presenter = new SplashPresenter(this, getSharedPreferences(SessionManager.PREF_NAME, 0));
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    @Override
    protected void onStart() {
        onAttachView();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        onDettachView();
        super.onDestroy();
    }

    @Override
    public void isSuccess() {
        MyFunction.intent(this, Main2Activity.class);
        finish();
    }

    @Override
    public void isFailed() {
        MyFunction.intent(this, SignIn.class);
        finish();
    }
}
