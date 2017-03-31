package me.neowang.market.di.component;

import com.jess.arms.di.scope.ActivityScope;

import common.AppComponent;
import dagger.Component;
import me.neowang.market.di.module.NewsModule;
import me.neowang.market.mvp.ui.fragment.NewsFragment;
import me.neowang.market.mvp.ui.fragment.NewsListFragment;


/**
 * Created by cd14 on 2017/3/16.
 */

@ActivityScope
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
    void inject(NewsListFragment newsListFragment);
}