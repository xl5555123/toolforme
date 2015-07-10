package com.xingliang.toolforme.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingliang.toolforme.R;

/**
 * Created by vector liu on 2015/4/16.
 */
public class FreeClassHistoryAdapter extends BaseAdapter{
    Context context;
    String history;
    String his[];
    public FreeClassHistoryAdapter(Context context, String history) {
        this.context = context;
        this.history = history;
        if(history!=null&&!history.equals("")){
            his = history.split(",");
        }else{
            his = new String[0];
        }
    }

    @Override
    public int getCount() {
        return his.length;
    }

    @Override
    public Object getItem(int position) {
        return his[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.classroom_history_item, null);
            holder.tv=(TextView) convertView.findViewById(R.id.classroom_tv);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }

        holder.tv.setText(his[position]);

        return convertView;
    }
    private class ViewHolder{
        TextView tv;
    }
}
