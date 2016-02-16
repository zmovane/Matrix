package io.github.leibnik.about_draw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Button> mList;

    public ListAdapter(Context context, List<Button> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    final class ViewHolder {
        private Button mButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            holder.mButton = mList.get(position);
            convertView = holder.mButton;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
