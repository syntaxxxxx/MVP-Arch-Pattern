package com.example.chatrealtimetracking.signUp;


import com.example.chatrealtimetracking.base.BaseView;

public interface SignUpContract {

    interface Presenter {

        void signUpUser(String file, String name, String email, String password, String level);

    }

    interface View extends BaseView {

        void isSignUpSuccess(String message);
        void isEmptyField(String message);

    }
}
