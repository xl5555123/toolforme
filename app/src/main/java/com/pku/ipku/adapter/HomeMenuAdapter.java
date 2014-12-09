package com.pku.ipku.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.navigation.HomeMenuFragment;

import java.util.List;


/**
 * Created by XingLiang on 2014/11/18.
 */
public class HomeMenuAdapter extends BaseAdapter {

    private final LayoutInflater listContainer;// 视图容器
    private final int itemViewResource;// 自定义项视图源
    private final List<Fragmentable> fragments;

    public HomeMenuAdapter(Context context, List<Fragmentable> fragmentables,
                           int resource) {
        listContainer = LayoutInflater.from(context);
        itemViewResource = resource;
        fragments = Lists.newArrayList(fragmentables);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;

        if (convertView == null) {
            // 获取list_item布局文件的视图
            convertView = listContainer
                    .inflate(this.itemViewResource, null);

            listItemView = new ListItemView();
            // 获取控件对象
            listItemView.title = (TextView) convertView
                    .findViewById(R.id.menu_text);
            listItemView.icon = (ImageView) convertView
                    .findViewById(R.id.menu_icon);

            // 设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }

        // 设置文字和图片
        Fragmentable fragmentable = fragments.get(position);
        Typable typable = (Typable) fragmentable;

        listItemView.title.setText(typable.getChineseName());
        listItemView.title.setTag(fragmentable);// 设置隐藏参数(实体类)
        listItemView.icon.setImageResource(fragmentable.getMenuIcon());
        if (0 == position) {
            listItemView.title.setTextColor(Color.argb(255, 255, 255, 255));
            listItemView.icon.setAlpha(255);
        }
        else {
            listItemView.title.setTextColor(Color.argb(150, 255, 255, 255));
            listItemView.icon.setAlpha(150);
        }
        return convertView;
    }

    class ListItemView { // 自定义控件集合
        public TextView title;
        public ImageView icon;
    }
}
