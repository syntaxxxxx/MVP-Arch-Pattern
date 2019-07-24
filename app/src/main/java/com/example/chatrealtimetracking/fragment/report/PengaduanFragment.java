package com.example.chatrealtimetracking.fragment.report;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.utils.SessionManager;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment implements PengaduanContract.View {

    @BindView(R.id.edt_pesang_pengaduan)
    EditText edtPesangPengaduan;
    @BindView(R.id.btn_send)
    Button btnSend;
    Unbinder unbinder;
    @BindView(R.id.btn_select)
    Button btnSelect;
    Unbinder unbinder1;
    @BindView(R.id.rb_admin_1)
    RadioButton rbAdmin1;
    @BindView(R.id.rb_admin_2)
    RadioButton rbAdmin2;
    @BindView(R.id.rb_admin_3)
    RadioButton rbAdmin3;
    Unbinder unbinder2;
    @BindView(R.id.tv_ada)
    TextView tvAda;
    private PengaduanPresenter presenter;
    private SessionManager manager;

    private static final int REQUEST_PDF = 0;

    String isi, level, path;

    public PengaduanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        manager = new SessionManager(getContext());
        initPresenter();
        initRb();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initPresenter() {
        presenter = new PengaduanPresenter(this);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    @OnClick({R.id.rb_admin_1, R.id.rb_admin_2, R.id.rb_admin_3, R.id.btn_send, R.id.btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_admin_1:
                level = "admin1";
                break;

            case R.id.rb_admin_2:
                level = "admin2";
                break;

            case R.id.rb_admin_3:
                level = "admin3";
                break;

            case R.id.btn_select:
                chooseFilePdf();
                break;

            case R.id.btn_send:
                presenter.addPengaduan(isi, path, manager.getID(), level);
                break;
        }
    }

    private void initRb() {
        if (rbAdmin1.isChecked()) {
            level = "admin1";
        } else if (rbAdmin2.isChecked()) {
            level = "admin2";
        } else {
            level = "admin3";
        }
    }

    private void chooseFilePdf() {
        new MaterialFilePicker()
                .withActivity(getActivity())
                .withRequestCode(REQUEST_PDF)
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PDF && resultCode == RESULT_OK && data != null && data.getData() != null) {
            path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Log.d("TAG", path);
            if (path != null) {
                tvAda.setText(R.string.ada_pdf);
            } else {
                Log.d("TAG", null);
            }
        }
    }

    @Override
    public void onShowSuccess(String message) {

    }

    @Override
    public void onShowError(String message) {

    }

    @Override
    public void onDestroy() {
        onDettachView();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
