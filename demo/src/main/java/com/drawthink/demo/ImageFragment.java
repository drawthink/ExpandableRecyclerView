package com.drawthink.demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.drawthink.demo.adapter.ImageAdapter;
import com.drawthink.demo.bean.ImageBean;
import com.drawthink.demo.itemdecoration.SimplePaddingDecoration;

import java.util.ArrayList;
import java.util.List;

import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class ImageFragment extends Fragment implements OnRecyclerViewListener.OnItemClickListener {


    private List<RecyclerViewData> mDatas;
    private RecyclerView mRecyclerview;
    private ImageAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.addItemDecoration(new SimplePaddingDecoration(getActivity()));
        initImages();
        adapter = new ImageAdapter(getActivity(), mDatas);
        adapter.setOnItemClickListener(this);
        mRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void initImages() {
        mDatas = new ArrayList<>();
        List<ImageBean> bean1 = new ArrayList<>();
        List<ImageBean> bean2 = new ArrayList<>();
        List<ImageBean> bean3 = new ArrayList<>();
        // 每个子列表长度可以不相同
        bean1.add(new ImageBean("Dog", R.mipmap.dog));
        bean1.add(new ImageBean("Dog", R.mipmap.dog));
        bean2.add(new ImageBean("Cat", R.mipmap.cat));
        bean3.add(new ImageBean("Bird", R.mipmap.bird));

        mDatas.add(new RecyclerViewData("Dog", bean1, true));
        mDatas.add(new RecyclerViewData("Cat", bean2, true));
        mDatas.add(new RecyclerViewData("Bird", bean3, true));
    }

    @Override
    public void onGroupItemClick(int position,int groupPosition, View view) {
        String group = (String) mDatas.get(groupPosition).getGroupData();
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + " group:" + group, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildItemClick(int position,int groupPosition, int childPosition, View view) {
        ImageBean bean = (ImageBean) mDatas.get(groupPosition).getChild(childPosition);
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + "  childPos:" + childPosition + " child:" + bean.getName(), Toast.LENGTH_SHORT).show();
    }


}