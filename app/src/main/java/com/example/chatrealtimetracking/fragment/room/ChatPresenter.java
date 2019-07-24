package com.example.chatrealtimetracking.fragment.room;

import android.util.Log;
import com.example.chatrealtimetracking.base.BasePresenter;
import com.example.chatrealtimetracking.fragment.room.model.DataItem;
import com.example.chatrealtimetracking.fragment.room.model.ResponseUser;
import com.example.chatrealtimetracking.networking.ConfigRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ChatPresenter implements BasePresenter<ChatContract.View>, ChatContract.Presenter {

    ChatContract.View chatView;

    public ChatPresenter(ChatContract.View chatView) {
        this.chatView = chatView;
    }

    @Override
    public void onAttach(ChatContract.View view) {
        chatView = view;
    }

    @Override
    public void onDettach() {
        chatView = null;
    }

    @Override
    public void onFetchListUser() {
        ConfigRetrofit.provideApiService().getListUser().enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response != null && response.isSuccessful()) {
                    boolean isSuccess = response.body().isIsSuccess();
                    List<DataItem> data = response.body().getData();

                    if (isSuccess) {
                        chatView.onShowListUser(data);
                    } else {
                        chatView.onShowListEmpty();
                    }

                } else {
                    chatView.isError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.d("TAG", t.getMessage());
                chatView.isError(t.getMessage());
            }
        });
    }
}
