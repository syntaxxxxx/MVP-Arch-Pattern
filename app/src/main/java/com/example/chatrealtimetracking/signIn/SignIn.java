package com.example.chatrealtimetracking.signIn;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.chatrealtimetracking.Main2Activity;
import com.example.chatrealtimetracking.R;
import com.example.chatrealtimetracking.signIn.model.Data;
import com.example.chatrealtimetracking.signUp.SignUp;
import com.example.chatrealtimetracking.utils.MyFunction;
import com.example.chatrealtimetracking.utils.SessionManager;

public class SignIn extends AppCompatActivity implements SignInContract.View {


    SignInPresenter presenter;

    String email, password, level;

    SessionManager manager;
    @BindView(R.id.edt_email_login)
    EditText edtEmailLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.rb_admin_1_login)
    RadioButton rbAdmin1Login;
    @BindView(R.id.rb_admin_2_login)
    RadioButton rbAdmin2Login;
    @BindView(R.id.rb_admin_3_login)
    RadioButton rbAdmin3Login;
    @BindView(R.id.rb_user_login)
    RadioButton rbUserLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        manager = new SessionManager(this);
        initPresenter();
        initRb();
    }

    private void initPresenter() {
        presenter = new SignInPresenter(this);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDettachView() {
        presenter.onDettach();
    }

    @Override
    protected void onStart() {
        onAttachView();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        onDettachView();
        super.onDestroy();
    }

    @OnClick({R.id.rb_admin_1_login, R.id.rb_admin_2_login, R.id.rb_admin_3_login, R.id.rb_user_login, R.id.btn_login, R.id.tv_register})
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
            case R.id.rb_user_login:
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                MyFunction.intent(this, SignUp.class);
                break;
        }
    }

    private void initRb() {
        if (rbAdmin1Login.isChecked()) {
            level = "admin1";
        } else if (rbAdmin2Login.isChecked()) {
            level = "admin2";
        } else if (rbAdmin3Login.isChecked()) {
            level = "admin3";
        } else {
            level = "user";
        }
    }

    private boolean validateEmail() {
        email = edtEmailLogin.getText().toString().trim();
        if (email.isEmpty()) {
            edtEmailLogin.setError(getString(R.string.is_empty_field));
            edtEmailLogin.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        password = edtPasswordLogin.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            edtPasswordLogin.setError(getString(R.string.is_empty_field));
            edtPasswordLogin.requestFocus();
            return false;
        }
        return true;
    }

    private void login() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        presenter.signInUser(
                email = edtEmailLogin.getText().toString().trim(),
                password = edtPasswordLogin.getText().toString().trim());
    }

    @Override
    public void setUpSession(Data data) {
        manager.setLogin(true);
        manager.setID(data.getId());
        manager.setUSER_EMAIL(data.getNama());
        MyFunction.intent(this, Main2Activity.class);
        finish();
    }

    @Override
    public void isSignSuccess() {
        MyFunction.intent(this, Main2Activity.class);
    }

    @Override
    public void isError(String message) {
        MyFunction.toast(this, message);
    }
}
