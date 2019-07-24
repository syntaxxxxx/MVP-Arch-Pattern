package com.example.chatrealtimetracking.signIn;


import com.example.chatrealtimetracking.base.BaseView;
import com.example.chatrealtimetracking.signIn.model.Data;

public interface SignInContract {

    interface Presenter {
        void signInUser(String name, String password);
    }

    interface View extends BaseView {
        void setUpSession(Data data);
        void isSignSuccess();
        void isError(String message);
    }
}
