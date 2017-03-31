package me.neowang.market.mvp.ui.activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import butterknife.BindView;
import common.AppComponent;
import common.WEActivity;
import me.neowang.market.R;
import me.neowang.market.mvp.ui.fragment.NewsFragment;
import me.neowang.market.mvp.ui.fragment.VideoFragment;

/**
 * Created by cd14 on 2017/3/16.
 */

public class HomeActivity extends WEActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private SparseArray<String> mSparseTags = new SparseArray<>();
    private int mItemId = -1;


    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_home, null, false);
    }

    @Override
    protected void initData() {
        initDrawerLayout(mDrawerLayout, mNavView);
        mSparseTags.put(R.id.nav_news, "News");
        mSparseTags.put(R.id.nav_photos, "Photos");
        mSparseTags.put(R.id.nav_videos, "Videos");
        mNavView.setCheckedItem(R.id.nav_news);
        addFragment(R.id.fl_container, new NewsFragment(), "News");
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    /**
     * 初始化 DrawerLayout
     *
     * @param drawerLayout DrawerLayout
     * @param navView      NavigationView
     */
    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
               showFragment();
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }
    private void showFragment(){
        switch (mItemId) {
            case R.id.nav_news:
                replaceFragment(R.id.fl_container, new NewsFragment(), mSparseTags.get(R.id.nav_news));
                break;
            case R.id.nav_photos:
                replaceFragment(R.id.fl_container, new NewsFragment(), mSparseTags.get(R.id.nav_photos));
                break;
            case R.id.nav_videos:
                replaceFragment(R.id.fl_container, new VideoFragment(), mSparseTags.get(R.id.nav_videos));
                break;
        }
        mItemId = -1;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }
        mItemId = item.getItemId();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}