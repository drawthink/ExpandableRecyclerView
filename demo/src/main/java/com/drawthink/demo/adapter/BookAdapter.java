package com.drawthink.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drawthink.demo.R;
import com.drawthink.demo.bean.Book;
import com.drawthink.demo.holder.BookViewHolder;

import java.util.List;

import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;

/**
 * author：Drawthink
 * describe：
 * date: 2017/5/22
 */

public class BookAdapter extends BaseRecyclerViewAdapter<String, Book,BookViewHolder> {

    private Context ctx;
    private LayoutInflater mInflater;

    public BookAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    /**
     * head View数据设置
     * @param holder
     * @param groupPos
     * @param position
     * @param groupData
     */
    @Override
    public void onBindGroupHolder(BookViewHolder holder, int groupPos,int position,String groupData) {
        holder.tvTitle.setText(groupData);
    }

    /**
     * child View数据设置
     * @param holder
     * @param groupPos
     * @param childPos
     * @param position
     * @param childData
     */
    @Override
    public void onBindChildpHolder(BookViewHolder holder, int groupPos,int childPos,int position, Book childData) {
        holder.tvName.setText(childData.getName());
    }

    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.title_item_layout,parent,false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_layout,parent,false);
    }

    @Override
    public BookViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new BookViewHolder(ctx,view,viewType);
    }

    /**
     * true 全部可展开
     * fasle  同一时间只能展开一个
     * */
    @Override
    public boolean canExpandAll() {
        return false;
    }
}
