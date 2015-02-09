package com.pku.ipku.adapter.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.PkuInfoType;

import java.util.List;

/**
 * Created by xingliang on 2015/2/9.
 */
public class SchoolNavigationAdapter extends BaseAdapter {

    private final List<PkuInfoType> pkuInfoTypeList;

    private final LayoutInflater layoutInflater;

    public SchoolNavigationAdapter(Context context, List<PkuInfoType> pkuInfoTypeList) {
        layoutInflater = LayoutInflater.from(context);
        this.pkuInfoTypeList = pkuInfoTypeList;
    }

    @Override
    public int getCount() {
        return pkuInfoTypeList.size();
    }

    @Override
    public Object getItem(int i) {
        return pkuInfoTypeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_person_grid_item, null);
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            PkuInfoType pkuInfoType = pkuInfoTypeList.get(i);
            if (imageView != null) {
                if (pkuInfoType != null) {
                    imageView.setImageResource(pkuInfoType.getIconId());
                    view.setTag(pkuInfoType);
                }
            }
        }
        return view;
    }
}
