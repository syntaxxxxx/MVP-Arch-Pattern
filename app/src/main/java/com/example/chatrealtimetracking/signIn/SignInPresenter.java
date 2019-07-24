package com.example.chatrealtimetracking.signIn;

import android.util.Log;
import com.example.chatrealtimetracking.base.BasePresenter;
import com.example.chatrealtimetracking.networking.ConfigRetrofit;
import com.example.chatrealtimetracking.signIn.model.Data;
import com.example.chatrealtimetracking.signIn.model.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenter implements BasePresenter<SignInContract.View>, SignInContract.Presenter {

    SignInContract.View signInView;

    public SignInPresenter(SignInContract.View signInView) {
        this.signInView = signInView;
    }

    @Override
    public void onAttach(SignInContract.View view) {
        signInView = view;
    }

    @Override
    public void onDettach() {
        signInView = null;
    }


    @Override
    public void signInUser(String name, String password) {
        ConfigRetrofit.provideApiService().signIn(name, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response != null && response.isSuccessful()) {
                    boolean isSuccess = response.body().isIsSuccess();
                    String message = response.body().getMsg();
                    Data dataUser = response.body().getData();

                    if (isSuccess) {
                        signInView.setUpSession(dataUser);
                        signInView.isSignSuccess();

                    } else {
                        signInView.isError(message);
                    }

                } else {
                    signInView.isError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d("TAG", "Failed Connection To" + t.getMessage());
                signInView.isError(t.getMessage());
            }
        });
    }
}
