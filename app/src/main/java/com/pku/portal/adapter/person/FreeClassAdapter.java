package com.pku.portal.adapter.person;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pku.portal.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by vector liu on 2015/4/16.
 */
public class FreeClassAdapter extends BaseAdapter{
    Context context;
    JSONArray classrooms;
    GridView gv;
    int len;
    String seletedBuiding;
    public FreeClassAdapter(Context context, JSONArray classrooms, GridView gv, String seletedBuiding) {
        this.context = context;
        this.classrooms = classrooms;
        this.gv = gv;
        len = classrooms.length();
        this.seletedBuiding = seletedBuiding;
    }

    @Override
    public int getCount() {
        return (len+1)*13;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.v("liuyi",position+"");
        ViewHolder holder;
        if (convertView==null) {
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.free_classroom_item, null);
//            AbsListView.LayoutParams param = new AbsListView.LayoutParams(
//                    android.view.ViewGroup.LayoutParams.FILL_PARENT,
//                    gv.getHeight()/(len+1));
//            convertView.setLayoutParams(param);
            holder.tv=(TextView) convertView.findViewById(R.id.block);
            holder.wrap_ll = (LinearLayout) convertView.findViewById(R.id.wrap_ll);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }

        if(position==0) {
            holder.tv.setText("#");
            holder.tv.setBackgroundResource(R.drawable.border);
        }
        else if(position < 13){
            holder.tv.setText(position+"");
            holder.tv.setBackgroundResource(R.drawable.border);
        }else {
            int index = position / 13 - 1;
            int off = position%13;
            JSONObject classroom = new JSONObject();
            String classRoomName = "";
            String info="";
            try {
                classroom = classrooms.getJSONObject(index);
                classRoomName = classroom.getString("room");
                if(off!=0)
                    info = classroom.getString("c"+off);
            }catch (Exception e){

            }
            if(off==0){
                Log.v("liuyi",classRoomName);
                holder.tv.setText(classRoomName.replace(seletedBuiding, ""));
                holder.tv.setBackgroundResource(R.drawable.border);
            }else{
                if(info!=null&&info.equals("占用")){
                    holder.tv.setBackgroundResource(R.drawable.border_filled);
                    holder.tv.setText("");
                }else{
                    holder.tv.setBackgroundResource(R.drawable.border);
                    holder.tv.setText("");
                }
            }
        }
        return convertView;
    }
    private class ViewHolder{
        LinearLayout wrap_ll;
        TextView tv;
    }



    public void setHeight(View ll, int height) {
        ll.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, height,
                Gravity.CENTER));

    }
}
