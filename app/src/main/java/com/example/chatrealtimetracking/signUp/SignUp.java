package com.example.chatrealtimetracking.signUp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.images.ImagesContract;
import com.example.chatrealtimetracking.images.ImagesPresenter;
import com.example.chatrealtimetracking.signIn.SignIn;
import com.example.chatrealtimetracking.utils.MyFunction;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class SignUp extends AppCompatActivity implements ImagesContract.View, SignUpContract.View {

    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;
    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.rb_admin_1)
    RadioButton rbAdmin;
    @BindView(R.id.rb_admin_2)
    RadioButton rbAdmin2;
    @BindView(R.id.rb_admin_3)
    RadioButton rbAdmi3;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    SignUpPresenter presenter;
    ImagesPresenter imagesPresenter;
    File filePicture;
    Compressor compressor;

    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_GALLERY = 1;

    public static final String allPermission[] = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    String name, email, password, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        compressor = new Compressor(this);
        initPresenter();
        initRb();
    }

    @OnClick({R.id.iv_profile, R.id.rb_admin_1, R.id.rb_admin_2, R.id.rb_admin_3, R.id.rb_user, R.id.btn_register, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_profile:
                showDialogSelectedImages();
                break;
            case R.id.rb_admin_1:
                level = "admin1";
                break;
            case R.id.rb_admin_2:
                level = "admin2";
                break;
            case R.id.rb_admin_3:
                level = "admin3";
                break;
            case R.id.rb_user:
                level = "user";
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_login:
                MyFunction.intent(this, SignIn.class);
                break;
        }
    }

    private void initPresenter() {
        presenter = new SignUpPresenter(this);
        imagesPresenter = new ImagesPresenter(this);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    private void initRb() {
        if (rbAdmin.isChecked()) {
            level = "admin1";
        } else if (rbAdmin2.isChecked()) {
            level = "admin2";
        } else if (rbAdmi3.isChecked()) {
            level = "admin3";
        } else {
            level = "user";
        }
    }

    private boolean validateName() {
        name = edtNama.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            edtNama.setError(getString(R.string.is_empty_field));
            edtNama.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        email = edtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            edtEmail.setError(getString(R.string.is_empty_field));
            edtEmail.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.is_empty_field));
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void register() {

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        name = edtNama.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        presenter.signUpUser(imagesPresenter.getImage(), name, email, password, level);
    }

    @Override
    public boolean checkSelfPermission() {
        for (String permission : allPermission) {
            int result = ActivityCompat.checkSelfPermission(this, permission);
            if (result == PackageManager.PERMISSION_DENIED) return false;
        }
        return true;
    }

    @Override
    public void showPermissionDialog(final boolean isGallery) {
        Dexter.withActivity(this)
                .withPermissions(allPermission).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                // check if all permission has granted
                if (report.areAllPermissionsGranted()) {
                    // something to do
                    if (isGallery) {
                        imagesPresenter.selectPicture();

                    } else {
                        imagesPresenter.takePicture();
                    }

                } else {

                }

                /**
                 * check for permanent denial of any permission
                 * show alert dialog navigating to Settings
                 * */
                if (report.isAnyPermissionPermanentlyDenied()) {
                    Log.d("TAG", "Go To Settings");
                } else {

                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                // paksa request permission lagi
                token.continuePermissionRequest();
            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                showErrorDialog();
            }
        }).onSameThread().check();
    }

    @Override
    public void takePicture(File file) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            if (file != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.chatrealtimetracking.fileprovider", file);
                takePicture.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    takePicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    filePicture = file;
                    startActivityForResult(takePicture, REQUEST_CAMERA);
                }
            } else {
                Log.d("TAG", "NULL");
            }
        } else {
            MyFunction.toast(this, getString(R.string.cant_take_picture));
        }
    }

    @Override
    public void selectPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, REQUEST_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            try {
                File file = new Compressor(this).compressToFile(filePicture);
                imagesPresenter.saveImage(file.getPath());
                imagesPresenter.showPreview(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                File file = new Compressor(this).compressToFile(new File((getRealPathFromUri(uri))));
                imagesPresenter.saveImage(file.getPath());
                imagesPresenter.showPreview(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void displayImagePreview(File file) {
        MyFunction.displayImagePreview(ivProfile, file);
    }

    @Override
    public void showDialogSelectedImages() {
        final CharSequence[] items = {getString(R.string.take_images), getString(R.string.choose_gallery), getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

        builder.setItems(items, (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Take Images")) {
                    imagesPresenter.takePicture();
                } else if (items[i].equals("Choose from Library")) {
                    imagesPresenter.selectPicture();
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        }));
        builder.show();
    }

    @Override
    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columnIndex);
        } finally {
            if (cursor != null) {
                cursor.close();
            } else {
                Log.d("TAG", "Cursor Null");
            }
        }
    }

    @Override
    public File newFile() {
        Calendar cal = Calendar.getInstance();
        long timeInMillis = cal.getTimeInMillis();
        String mFileName = String.valueOf(timeInMillis) + ".jpeg";
        File filePath = getFilePath();
        try {
            File newFile = new File(filePath.getAbsolutePath(), mFileName);
            newFile.createNewFile();
            return newFile;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File getFilePath() {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    @Override
    public void showErrorDialog() {
        MyFunction.toast(this, "Tidak Ada Gambar");
    }

    @Override
    public void isEmptyField(String message) {
        MyFunction.toast(this, message);
    }

    @Override
    public void isSignUpSuccess(String message) {
        MyFunction.intent(this, SignIn.class);
        MyFunction.toast(this, message);
    }
}