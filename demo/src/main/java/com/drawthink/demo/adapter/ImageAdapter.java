package com.drawthink.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.drawthink.demo.R;
import com.drawthink.demo.bean.ImageBean;
import com.drawthink.demo.holder.ImageViewHolder;

import java.util.List;

import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;

/**
 * author：Drawthink
 * describe：
 * date: 2017/5/25
 */

public class ImageAdapter extends BaseRecyclerViewAdapter<String,ImageBean,ImageViewHolder> {

    private Context ctx;
    private List datas;
    private LayoutInflater mInflater;

    public ImageAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.datas = datas;
    }

    @Override
    public void onBindGroupHolder(ImageViewHolder holder, int groupPos,int position, String groupData) {
        holder.tvTitle.setText(groupData);
    }

    @Override
    public void onBindChildpHolder(ImageViewHolder holder, int groupPos,int childPos,int position, ImageBean childData) {
        holder.image.setBackgroundResource(childData.getResId());
    }

    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.title_item_layout,parent,false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_image_layout,parent,false);
    }

    @Override
    public ImageViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new ImageViewHolder(ctx,view,viewType);
    }
}
