package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.portal.R;

import java.util.List;

/**
 * Created by vector liu on 2015/4/8.
 */
public class SelectAdapter extends BaseAdapter {
    List<String> itemToSelect;
    Context context;
    public SelectAdapter(Context context, List<String> itemToSelect){
        this.context = context;
        this.itemToSelect = itemToSelect;
    }
    @Override
    public int getCount() {
        return itemToSelect.size();
    }

    @Override
    public Object getItem(int position) {
        return itemToSelect.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.select_item,null);
        TextView content = (TextView)convertView.findViewById(R.id.select_content_tv);
        content.setText(itemToSelect.get(position));
        return convertView;
    }
}
