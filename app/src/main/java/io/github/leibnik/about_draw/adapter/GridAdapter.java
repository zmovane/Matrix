package io.github.leibnik.about_draw.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Droidroid on 2016/1/29.
 */
public class GridAdapter extends BaseAdapter {

    private List<EditText> mEds;

    public GridAdapter(List<EditText> eds){
        mEds = eds;
    }

    @Override
    public int getCount() {
        return mEds.size();
    }

    @Override
    public Object getItem(int position) {
        return mEds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    final class ViewHolder {
        EditText ed;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            holder.ed = mEds.get(position);
            convertView = holder.ed;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
