package me.neowang.market.mvp.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import common.AppComponent;
import common.WEFragment;
import me.neowang.market.R;
import me.neowang.market.di.component.DaggerNewsComponent;
import me.neowang.market.di.module.NewsModule;
import me.neowang.market.mvp.contract.NewsContract;
import me.neowang.market.mvp.presenter.NewsPresenter;
import me.neowang.market.mvp.ui.adapter.ViewPagerAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by cd14 on 2017/3/16.
 */

public class NewsFragment extends WEFragment<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    ViewPagerAdapter mPagerAdapter;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerNewsComponent
                .builder()
                .appComponent(appComponent)
                .newsModule(new NewsModule(this))//请将NewsModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected void initData() {
        initToolBar(mToolBar, true, "新闻");
        setHasOptionsMenu(true);
        mPagerAdapter = new ViewPagerAdapter(this.getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 5; i++ ) {
            titles.add("新闻");
            fragments.add(NewsListFragment.newInstance());
        }
        mPagerAdapter.setItems(fragments, titles);
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传bundle,里面存一个what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事,和message同理
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在内部onActivityCreated中
     * 初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

}