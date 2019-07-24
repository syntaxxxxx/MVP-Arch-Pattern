package com.example.chatrealtimetracking.signUp;

import android.util.Log;
import com.example.chatrealtimetracking.base.BasePresenter;
import com.example.chatrealtimetracking.networking.ConfigRetrofit;
import com.example.chatrealtimetracking.signUp.model.ResponseRegister;
import com.example.chatrealtimetracking.utils.MyFunction;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements BasePresenter<SignUpContract.View>, SignUpContract.Presenter {

    SignUpContract.View signUpView;

    public SignUpPresenter(SignUpContract.View signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void onAttach(SignUpContract.View view) {
        signUpView = view;
    }

    @Override
    public void onDettach() {
        signUpView = null;
    }

    @Override
    public void signUpUser(String file, String name, String email, String password, String level) {
        RequestBody reqName = MyFunction.createPartFromString(name);
        RequestBody reqEmail = MyFunction.createPartFromString(email);
        RequestBody reqPassword = MyFunction.createPartFromString(password);
        RequestBody reqLevel = MyFunction.createPartFromString(level);
        ConfigRetrofit.provideApiService().signUp(reqName, reqEmail, reqPassword, reqLevel,
                MyFunction.createMultipartBody(file)).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response != null && response.isSuccessful()) {
                    String msg = response.body().getMsg();
                    Log.d("TAG", "Success Guys");
                    signUpView.isSignUpSuccess(msg);

                } else {
                    Log.d("TAG", response.errorBody().toString());
                    signUpView.isEmptyField(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Log.d("TAG", "Failed To Connection To" + t.getMessage());
                signUpView.isEmptyField(t.getMessage());
            }
        });
    }
}
