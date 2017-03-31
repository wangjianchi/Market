package me.neowang.market.mvp.ui.holder;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.widget.imageloader.ImageLoader;

import butterknife.BindView;
import common.WEApplication;
import me.neowang.market.R;
import me.neowang.market.mvp.model.entity.LocalVideo;
import me.neowang.market.utils.ShortCutUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.provider.MediaStore.Video.Thumbnails.MINI_KIND;

/**
 * Created by cd14 on 2017/3/23.
 */

public class LocalVideoItemHolder extends BaseHolder<LocalVideo>{
    @Nullable
    @BindView(R.id.iv_avatar)
    ImageView mAvater;
    @Nullable
    @BindView(R.id.tv_name)
    TextView mName;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    public LocalVideoItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(final LocalVideo data, int position) {
        Observable.just(data.getTitle())
                .subscribe(RxTextView.text(mName));
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                subscriber.onNext(ShortCutUtils.getVideoThumbnail(data.getPath(),350,300,MINI_KIND));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                mAvater.setImageBitmap(bitmap);
            }
        });
    }
}
