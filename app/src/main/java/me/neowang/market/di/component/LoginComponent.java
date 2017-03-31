package me.neowang.market.di.component;

import com.jess.arms.di.scope.ActivityScope;

import common.AppComponent;
import dagger.Component;
import me.neowang.market.di.module.LoginModule;
import me.neowang.market.mvp.ui.activity.LoginActivity;


/**
 * Created by cd14 on 2017/3/17.
 */

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}