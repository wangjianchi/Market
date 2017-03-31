package me.neowang.market.di.component;

import com.jess.arms.di.scope.ActivityScope;

import common.AppComponent;
import dagger.Component;
import me.neowang.market.di.module.VideoModule;
import me.neowang.market.mvp.ui.fragment.LocalVideoFragment;


/**
 * Created by cd14 on 2017/3/23.
 */

@ActivityScope
@Component(modules = VideoModule.class, dependencies = AppComponent.class)
public interface VideoComponent {
    void inject(LocalVideoFragment fragment);
}