package me.neowang.market.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import me.neowang.market.R;
import me.neowang.market.mvp.model.entity.LocalVideo;
import me.neowang.market.mvp.ui.holder.LocalVideoItemHolder;

/**
 * Created by cd14 on 2017/3/23.
 */

public class LocalVideoAdapter extends DefaultAdapter<LocalVideo>{

    public LocalVideoAdapter(List<LocalVideo> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<LocalVideo> getHolder(View v, int viewType) {
        return new LocalVideoItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list;
    }
}
