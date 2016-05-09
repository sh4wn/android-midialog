package li.xiangyang.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

import li.xiangyang.android.midialog.R;

/**
 * Created by bac on 15/6/30.
 */
public class CommonListAdapter<T> extends BaseAdapter {

    Context cxt;
    List<T> items;
    Set<Long> splitItems;
    int itemLayoutResId;
    int groupLayoutResId;
    WeakReference<ViewFormater> formater;

    public CommonListAdapter(Context cxt, List<T> items, Set<Long> splitItems, int itemLayoutResId, int groupLayoutResId, ViewFormater<T> formater){
        this.cxt=cxt;
        this.items=items;
        this.splitItems=splitItems;
        this.formater=new WeakReference<ViewFormater>(formater);
        this.itemLayoutResId=itemLayoutResId;
        this.groupLayoutResId=groupLayoutResId;
    }

    public void changeItems(List<T> items, Set<Long> splitItems){
        this.items=items;
        this.splitItems=splitItems;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean group=splitItems!=null && splitItems.contains((long)position);
        if (convertView == null || !Boolean.valueOf(group).equals(convertView.getTag(R.layout.list_group_header))) {
            if (group){
                convertView = LayoutInflater.from(cxt).inflate(groupLayoutResId, parent,false);
                convertView.setTag(R.layout.list_group_header,true);
            }else{
                convertView = LayoutInflater.from(cxt).inflate(itemLayoutResId, parent,false);
                convertView.setTag(R.layout.list_group_header, false);
            }
        }
        formater.get().formatItemView(items.get(position), convertView,position,group);
        return convertView;
    }

    public static interface ViewFormater<T>{
        void formatItemView(T item, View view, int index, boolean group);
    }
}
