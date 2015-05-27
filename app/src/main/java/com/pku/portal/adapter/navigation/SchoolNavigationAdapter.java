package com.pku.portal.adapter.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.model.pkuInfo.RegisterInPkuInfoPage;

import java.util.List;

/**
 * Created by xingliang on 2015/2/9.
 */
public class SchoolNavigationAdapter extends BaseAdapter {

    private final List<RegisterInPkuInfoPage> registerInPkuInfoPages;

    private final LayoutInflater layoutInflater;

    public SchoolNavigationAdapter(Context context, List<RegisterInPkuInfoPage> registerInPkuInfoPages) {
        layoutInflater = LayoutInflater.from(context);
        this.registerInPkuInfoPages = registerInPkuInfoPages;
    }

    @Override
    public int getCount() {
        return registerInPkuInfoPages.size();
    }

    @Override
    public Object getItem(int i) {
        return registerInPkuInfoPages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_person_grid_item, null);
        if (view != null) {
            RegisterInPkuInfoPage registerInPkuInfoPage = registerInPkuInfoPages.get(i);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            view.findViewById(R.id.background).setBackgroundResource(registerInPkuInfoPage.getPageBackgroundId());
            if (icon != null) {
                icon.setImageResource(registerInPkuInfoPage.getPageDrawableId());
            }
            TextView title = (TextView) view.findViewById(R.id.title);
            if (title != null) {
                title.setText(registerInPkuInfoPage.getPageTitle());
            }
            view.setTag(registerInPkuInfoPage);
        }
        return view;
    }
}
