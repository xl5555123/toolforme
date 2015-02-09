package com.pku.ipku.adapter.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;

import java.util.List;

/**
 * Created by xingliang on 2015/2/9.
 */
public class PersonNavigationAdapter extends BaseAdapter {

    private final List<RegisterInPersonPage> registerInPersonPageList;

    private final LayoutInflater layoutInflater;

    public PersonNavigationAdapter(Context context, List<RegisterInPersonPage> registerInPersonPageList) {
        this.registerInPersonPageList = registerInPersonPageList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return registerInPersonPageList.size();
    }

    @Override
    public Object getItem(int i) {
        return registerInPersonPageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_person_grid_item, null);
        if (view != null) {
            RegisterInPersonPage registerInPersonPage = registerInPersonPageList.get(i);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            if (icon != null) {
                icon.setImageResource(registerInPersonPage.getPageDrawableId());
            }
            view.setTag(registerInPersonPage);
        }
        return view;
    }
}
