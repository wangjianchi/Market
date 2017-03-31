package me.neowang.market.di.component;

import com.jess.arms.di.scope.ActivityScope;

import common.AppComponent;
import dagger.Component;
import me.neowang.market.di.module.UserModule;
import me.neowang.market.mvp.ui.activity.UserActivity;

/**
 * Created by jess on 9/4/16 11:17
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);
}
