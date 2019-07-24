package com.example.chatrealtimetracking.images;

import java.io.File;

public class ImagesPresenter implements ImagesContract.Presenter {

    private final ImagesContract.View view;
    private String selectedFile;

    public ImagesPresenter(ImagesContract.View view) {
        this.view = view;
    }

    @Override
    public void takePicture() {
        if (!view.checkSelfPermission()) {
            view.showPermissionDialog(false);
            return;
        }

        File file = view.newFile();

        if (file == null) {
            view.showErrorDialog();
            return;
        }
        view.takePicture(file);
    }

    @Override
    public void selectPicture() {
        if (!view.checkSelfPermission()) {
            view.showPermissionDialog(true);
            return;
        }
        view.selectPicture();
    }

    @Override
    public void saveImage(String filePath) {
        selectedFile = filePath;
    }

    @Override
    public String getImage() {
        return selectedFile;
    }

    @Override
    public void showPreview(File file) {
        view.displayImagePreview(file);
    }
}
