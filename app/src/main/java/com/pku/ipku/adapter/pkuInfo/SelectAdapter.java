package com.pku.ipku.adapter.pkuInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pku.ipku.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vector liu on 2015/4/8.
 */
public class SelectAdapter extends BaseAdapter {
    List<String> itemToSelect;
    public List<Boolean> mChecked;
    Context context;
    public SelectAdapter(Context context, List<String> itemToSelect){
        this.context = context;
        this.itemToSelect = itemToSelect;
        mChecked = new ArrayList<Boolean>();
        for(int i=0;i<itemToSelect.size();i++){
            mChecked.add(false);
        }
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
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.check_cb);
        final int p = position;
        cb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox)v;
                mChecked.set(p, cb.isChecked());
            }
        });
        content.setText(itemToSelect.get(position));
        return convertView;
    }
}
