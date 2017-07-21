#####代码[github地址](https://github.com/drawthink/ExpandableRecyclerView)

因为工作需要一个二级树结构View,所以借鉴ExpandableListView自己写了个ExpandableRecyclerView。

1.目前只支持两级结构。

2.支持所有组同时全部展开。

3.支持同一时间只能展开一组。

4.支持初始化数据时，指定展开某组数据。

效果图：

![](https://github.com/drawthink/ExpandableRecyclerView/blob/master/screenshot/screenshot.gif?raw=true)
  
使用步骤：加入依赖
```
compile 'com.drawthink:expandable-recyclerview:0.0.3'

```
####1.继承BaseViewHolder，实现自己的ViewHolder

#####1.1 在构造函数中初始化你的View(包括GroupView,和childView).

#####1.2 分别实现以下两个方法，并在对应方法中返回对应Layout布局文件中根节点的ID。

```
public int getGroupViewResId()

public int getChildViewResId()
```
示例代码：
```
public class ImageViewHolder extends BaseViewHolder {

    public ImageView image;
    public TextView tvTitle;

    /**
     *  初始化你的View(这里包括GroupView,和childView)
     */
    public ImageViewHolder(Context ctx, View itemView, int viewType) {
        super(ctx,itemView, viewType);
        image = (ImageView) itemView.findViewById(R.id.iv_image);
        tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
    }

    /**
     * @return 返回你的GroupView 布局文件中根节点的ID
     */
    @Override
    public int getGroupViewResId() {
        return R.id.group;
    }

    /**
     * @return 返回你的ChildView 布局文件中根节点的ID
     */
    @Override
    public int getChildViewResId() {
        return R.id.child;
    }

}
```

####2.继承BaseRecyclerViewAdapter<T,S,VH extends BaseViewHolder> ，完成自己的Adapter。

T,S,VH各参数见如下注释
```
/**
 * author：Drawthink
 * describe:
 * date: 2017/5/22
 * T :group  data
 * S :child  data
 * VH :ViewHolder
 */

public abstract class BaseRecyclerViewAdapter<T,S,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>

```
示例Adapter代码：
```
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
```

完成以上两步之后，基本大工告成，由于ExpandableRecyclerView的数据是要分组的，所以提供了RecyclerViewData来封装
```
/**
     * @param groupData
     * @param childDatas
     * @param isExpand   初始化展示数据时，该组数据是否展开
     */
public RecyclerViewData(T groupData, List<S> childDatas,boolean isExpand)

```
那接下来看下数据具体是怎样封装的。
```
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
```
所有工作以完成，现在你可以象平常使用Adapter，RecyclerView一样，来愉快的写代码了。

#####注意：在对元数据mDatas进行增删操作时，要调用adapter.notifyRecyclerViewData();否则会造成数据索引错乱的问题。


#####代码[github地址](https://github.com/drawthink/ExpandableRecyclerView)