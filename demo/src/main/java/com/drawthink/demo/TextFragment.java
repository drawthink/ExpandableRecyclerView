package com.drawthink.demo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.drawthink.demo.adapter.BookAdapter;
import com.drawthink.demo.bean.Book;
import com.drawthink.demo.itemdecoration.SimplePaddingDecoration;

import java.util.ArrayList;
import java.util.List;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class TextFragment extends Fragment implements OnRecyclerViewListener.OnItemClickListener, OnRecyclerViewListener.OnItemLongClickListener{


    private List<RecyclerViewData> mDatas;
    private RecyclerView mRecyclerview;
    private BookAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        initBooks();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.addItemDecoration(new SimplePaddingDecoration(getActivity()));

        adapter = new BookAdapter(getActivity(), mDatas);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);

        mRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void initBooks() {
        List<Book> bean1 = new ArrayList<>();
        List<Book> bean2 = new ArrayList<>();
        List<Book> bean3 = new ArrayList<>();
        List<Book> bean4 = new ArrayList<>();
        // id , pid , label , 其他属性
        bean1.add(new Book("文件管理系统"));
        bean1.add(new Book("游戏"));
        bean1.add(new Book("文档"));
        bean1.add(new Book("程序"));
        bean2.add(new Book("war3"));
        bean2.add(new Book("刀塔传奇"));

        bean1.add(new Book("面向对象"));
        bean2.add(new Book("非面向对象"));

        bean2.add(new Book("C++"));
        bean2.add(new Book("JAVA"));
        bean2.add(new Book("Javascript"));
        bean2.add(new Book("C"));

        bean3.add(new Book("文件管理系统"));
        bean3.add(new Book("游戏"));
        bean4.add(new Book("文档"));
        bean4.add(new Book("程序"));
        bean4.add(new Book("war3"));
        bean3.add(new Book("刀塔传奇"));

        bean3.add(new Book("面向对象"));
        bean4.add(new Book("非面向对象"));

        bean3.add(new Book("文件管理系统"));
        bean3.add(new Book("游戏"));
        bean4.add(new Book("文档"));
        bean4.add(new Book("程序"));
        bean4.add(new Book("war3"));
        bean4.add(new Book("刀塔传奇"));

        mDatas.add(new RecyclerViewData("分组0", bean1, true));
        mDatas.add(new RecyclerViewData("分组1", bean2, true));
        mDatas.add(new RecyclerViewData("分组2", bean3, true));
        mDatas.add(new RecyclerViewData("分组3", bean4, true));
    }

    @Override
    public void onGroupItemClick(int position,int groupPosition, View view) {
        String group = (String) mDatas.get(groupPosition).getGroupData();
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + " group:" + group, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildItemClick(int position,int groupPosition, int childPosition, View view) {
        Book bean = (Book) mDatas.get(groupPosition).getChild(childPosition);
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + "  childPos:" + childPosition + " child:" + bean.getName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onGroupItemLongClick(int position,int groupPosition, View view) {
        String group = (String) mDatas.get(groupPosition).getGroupData();
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + " group:" + group, Toast.LENGTH_SHORT).show();
        showDeleteDialog(position,groupPosition,0,true);
    }

    @Override
    public void onChildItemLongClick(int position,int groupPosition, int childPosition, View view) {
        Book bean = (Book) mDatas.get(groupPosition).getChild(childPosition);
        Toast.makeText(getActivity(), "groupPos:" + groupPosition + "  childPos:" + childPosition + " child:" + bean.getName(), Toast.LENGTH_SHORT).show();
        showDeleteDialog(position,groupPosition,childPosition,false);
    }

    /**
     * 删除数据
     * @param position
     * @param isGroup
     */
    private void showDeleteDialog(final int position, final int groupPosition, final int childPosition, final boolean isGroup){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("提示!")
                .setMessage(isGroup?"您确定要删除此组数据":"您确定要删除此条数据")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //然后根据回调里的groupPosition和childPosition来更新你的数据源
                        if(isGroup){
                            mDatas.remove(groupPosition);
                        }else {
                            mDatas.get(groupPosition).removeChild(childPosition);
                        }
                        adapter.notifyRecyclerViewData();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
