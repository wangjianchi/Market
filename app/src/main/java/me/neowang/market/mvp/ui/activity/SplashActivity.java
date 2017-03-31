package me.neowang.market.mvp.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import common.AppComponent;
import common.WEActivity;
import me.neowang.market.R;
import me.neowang.market.utils.RxHelper;
import rx.Subscriber;

/**
 * Created by cd14 on 2017/3/16.
 */

public class SplashActivity extends WEActivity {


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_splash, null, false);
    }

    @Override
    protected void initData() {
        RxHelper.countdown(1)
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }




}