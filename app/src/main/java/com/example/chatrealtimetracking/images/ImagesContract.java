package com.example.chatrealtimetracking.images;

import android.net.Uri;

import java.io.File;

public class ImagesContract {

    public interface View {

        boolean checkSelfPermission();

        void showPermissionDialog(boolean isGallery);

        void takePicture(File file);

        void selectPicture();

        void displayImagePreview(File file);

        void showDialogSelectedImages();

        void showErrorDialog();

        String getRealPathFromUri(Uri contentUri);

        File newFile();

        File getFilePath();

    }

    interface Presenter {

        void takePicture();

        void selectPicture();

        void showPreview(File file);

        void saveImage(String filePath);

        String getImage();
    }
}
