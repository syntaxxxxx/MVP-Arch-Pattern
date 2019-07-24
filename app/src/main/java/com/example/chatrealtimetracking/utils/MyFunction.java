package com.example.chatrealtimetracking.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import com.squareup.picasso.Picasso;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

public class MyFunction {

    public static void toast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void intent(Context ctx, Class intent) {
        ctx.startActivity(new Intent(ctx, intent));
    }

    public static void displayImagePreview(ImageView iv, File file) {
        Picasso.get().load(file).into(iv);
    }

    public static void displayImagesOriginal( ImageView iv, String url) {
        Picasso.get().load(url).into(iv);

    }

    public static void displayImagesThumbnail(Context ctx, ImageView iv, String url, float thumbnail) {

    }

    public static View inflate(Context context, @LayoutRes int layout, ViewGroup vg, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(layout, vg, attachToRoot);
    }

    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * Create request body for image resource
     *
     * @param file
     * @return
     */
    public static RequestBody createRequestForImage(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }

    /**
     * Create request body for file pdf resource
     *
     * @param file
     * @return
     */
    public static RequestBody createRequestForFile(File file) {
        return RequestBody.create(MediaType.parse("*/*"), file);
    }

    /**
     * Create request body for string
     *
     * @param string
     * @return
     */
    public static RequestBody createPartFromString(String string) {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), string);
    }

    /**
     * return multipart part request body
     *
     * @param filePath
     * @return
     */
    public static MultipartBody.Part createMultipartBody(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = createRequestForImage(file);
        return MultipartBody.Part.createFormData("foto", file.getName(), requestBody);
    }

    public static MultipartBody.Part createMultipartBody2(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = createRequestForFile(file);
        return MultipartBody.Part.createFormData("uploadfile", file.getName(), requestBody);
    }
}
