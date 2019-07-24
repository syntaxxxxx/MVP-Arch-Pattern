package com.example.chatrealtimetracking.networking;

import com.example.chatrealtimetracking.fragment.report.model.ResponsePengaduan;
import com.example.chatrealtimetracking.fragment.room.model.ResponseUser;
import com.example.chatrealtimetracking.signIn.model.ResponseLogin;
import com.example.chatrealtimetracking.signUp.model.ResponseRegister;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @Multipart
    @POST("get-pengguna-add.php")
    Call<ResponseRegister> signUp(
            @Part("nama") RequestBody nama,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("level") RequestBody level,
            @Part MultipartBody.Part images);

    @FormUrlEncoded
    @POST("get-pengguna-login.php")
    Call<ResponseLogin> signIn(
            @Field("nama") String nama,
            @Field("password") String email);

    @GET("get-pengguna-view.php")
    Call<ResponseUser> getListUser();

    @Multipart
    @POST("get-pengaduan-add.php")
    Call<ResponsePengaduan> pengaduan(
            @Part("isi") RequestBody isi,
            @Part MultipartBody.Part file,
            @Part("pengguna_id") RequestBody penggunaan_id,
            @Part("admin_id") RequestBody admin_id);


//    @Multipart
//    @POST("registeruser.php")
//    Single<ResponseBody> register(
//            @Part("vsnama") RequestBody nama,
//            @Part("vsemail") RequestBody email,
//            @Part("vspassword") RequestBody password,
//            @Part("vslevel") RequestBody level,
//            @Part MultipartBody.Part images);
//
//    @FormUrlEncoded
//    @POST("loginuser.php")
//    Call<ResponseRegister> login(
//            @Field("edtemail") String email,
//            @Field("edtpassword") String password,
//            @Field("vslevel") String level);
//
//    @GET("api/get-banner.php")
//    Call<ResponseImageSlider> getImagesSlider(
//            @Query("idlogin") String idlogin
//    );
//
//    @GET("api/get-news.php")
//    Call<ResponseBerita> getBerita(
//            @Query("idlogin") String idlogin
//    );
}