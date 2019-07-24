package com.example.chatrealtimetracking.fragment.report;

import android.util.Log;
import com.example.chatrealtimetracking.base.BasePresenter;
import com.example.chatrealtimetracking.fragment.report.model.ResponsePengaduan;
import com.example.chatrealtimetracking.networking.ConfigRetrofit;
import com.example.chatrealtimetracking.utils.MyFunction;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanPresenter implements BasePresenter<PengaduanContract.View>, PengaduanContract.Presenter {

    PengaduanContract.View pengaduanView;

    public PengaduanPresenter(PengaduanContract.View pengaduanView) {
        this.pengaduanView = pengaduanView;
    }

    @Override
    public void onAttach(PengaduanContract.View view) {
        pengaduanView = view;
    }

    @Override
    public void onDettach() {
        pengaduanView = null;
    }

    @Override
    public void addPengaduan(String isi, String file, String pengunaan_id, String admin_id) {
        RequestBody reqIsi = MyFunction.createPartFromString(isi);
        RequestBody reqPengunaanId = MyFunction.createPartFromString(pengunaan_id);
        RequestBody reqAdminId = MyFunction.createPartFromString(admin_id);
        ConfigRetrofit.provideApiService().pengaduan(
                reqIsi,
                MyFunction.createMultipartBody2(file),
                reqPengunaanId,
                reqAdminId).enqueue(new Callback<ResponsePengaduan>() {
            @Override
            public void onResponse(Call<ResponsePengaduan> call, Response<ResponsePengaduan> response) {
                if (response != null && response.isSuccessful()) {
                    boolean isSuccess = response.body().isIsSuccess();
                    String message = response.body().getMsg();

                    if (isSuccess) {
                        Log.d("TAG", message);
                        pengaduanView.onShowSuccess(message);
                    } else {
                        pengaduanView.onShowError(message);
                    }

                } else {
                    pengaduanView.onShowError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponsePengaduan> call, Throwable t) {
                Log.d("TAG", t.getMessage());
                pengaduanView.onShowError(t.getMessage());
            }
        });
    }
}
