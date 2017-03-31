package me.neowang.market.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import me.neowang.market.mvp.contract.LoginContract;
import me.neowang.market.mvp.model.api.cache.CacheManager;
import me.neowang.market.mvp.model.api.service.ServiceManager;


/**
 * Created by cd14 on 2017/3/17.
 */

@ActivityScope
public class LoginModel extends BaseModel<ServiceManager, CacheManager> implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestory() {
        super.onDestory();
        this.mGson = null;
        this.mApplication = null;
    }

}