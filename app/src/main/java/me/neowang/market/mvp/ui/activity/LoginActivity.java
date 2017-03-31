package me.neowang.market.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;
import me.neowang.market.R;
import me.neowang.market.di.component.DaggerLoginComponent;
import me.neowang.market.di.module.LoginModule;
import me.neowang.market.mvp.contract.LoginContract;
import me.neowang.market.mvp.presenter.LoginPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by cd14 on 2017/3/17.
 */

public class LoginActivity extends WEActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_mail)
    EditText et_mail;
    @BindView(R.id.et_password)
    EditText et_password;
    @OnClick(R.id.btn_login) void login(){
        String username = et_mail.getText().toString();
        String password = et_password.getText().toString();
    }



    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this)) //请将LoginModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null, false);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


}