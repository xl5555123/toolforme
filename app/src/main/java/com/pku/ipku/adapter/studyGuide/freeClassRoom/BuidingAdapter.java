package com.pku.ipku.adapter.studyGuide.freeClassRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.util.AppContextHolder;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class BuidingAdapter extends BaseAdapter {

    private final LayoutInflater listContainer;// 视图容器
    private List<String> buidings;

    public BuidingAdapter(List<String> buidings) {
        listContainer = LayoutInflater.from(AppContextHolder.getAppContext());
        this.buidings = buidings;
    }

    @Override
    public int getCount() {
        return buidings.size();
    }

    @Override
    public Object getItem(int position) {
        return buidings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = listContainer.inflate(R.layout.building_item, null);
        TextView buidingName = (TextView) convertView.findViewById(R.id.buiding_name);
        buidingName.setText(buidings.get(position));
        convertView.setTag(buidings.get(position));
        return convertView;
    }
}
