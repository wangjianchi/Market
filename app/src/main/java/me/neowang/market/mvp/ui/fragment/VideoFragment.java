package me.neowang.market.mvp.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import common.AppComponent;
import common.WEFragment;
import me.neowang.market.R;
import me.neowang.market.mvp.ui.adapter.ViewPagerAdapter;


/**
 * Created by cd14 on 2017/3/23.
 */

public class VideoFragment extends WEFragment {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    ViewPagerAdapter mPagerAdapter;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    protected void initData() {
        initToolBar(mToolBar, true, "视频");
        setHasOptionsMenu(true);
        mPagerAdapter = new ViewPagerAdapter(this.getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("本地视频");
        titles.add("在线视频");
        fragments.add(LocalVideoFragment.newInstance());
        fragments.add(NewsListFragment.newInstance());
        mPagerAdapter.setItems(fragments, titles);
    }
}