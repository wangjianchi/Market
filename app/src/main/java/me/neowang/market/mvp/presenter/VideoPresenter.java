package me.neowang.market.mvp.presenter;

import android.app.Application;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.google.gson.Gson;
import com.jess.arms.base.AppManager;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.WEApplication;
import me.neowang.market.mvp.contract.VideoContract;
import me.neowang.market.mvp.model.entity.LocalVideo;
import me.neowang.market.mvp.ui.adapter.LocalVideoAdapter;
import me.neowang.rxerrorhandler.core.RxErrorHandler;


/**
 * Created by cd14 on 2017/3/23.
 */

@ActivityScope
public class VideoPresenter extends BasePresenter<VideoContract.Model, VideoContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    List<LocalVideo> videos = new ArrayList<>();
    private DefaultAdapter mAdapter;

    @Inject
    public VideoPresenter(VideoContract.Model model, VideoContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        mAdapter = new LocalVideoAdapter(videos);
        mRootView.setAdapter(mAdapter);//设置Adapter
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getLocalVideo(){
        videos.clear();
        Cursor cursor = WEApplication.getContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            LocalVideo video = new LocalVideo();
            video.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)));
            video.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
            video.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));
            video.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
            video.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)));
            video.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
            video.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
            video.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
            video.setResolution(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)));
            videos.add(video);
        }
        Gson gson = new Gson();
        Log.i("VideoPresenter", "getLocalVideo: "+gson.toJson(videos));
        mAdapter.notifyDataSetChanged();//通知更新数据
        mRootView.hideLoading();
    }

}