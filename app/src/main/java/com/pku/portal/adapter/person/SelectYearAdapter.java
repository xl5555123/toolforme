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
 * Created by xingliang on 2015/2/11.
 */
public class SelectYearAdapter extends BaseAdapter {

    private final List<String> years;
    private final LayoutInflater layoutInflater;
    private final int index;

    public SelectYearAdapter(List<String> years, Context context, int index) {
        layoutInflater = LayoutInflater.from(context);
        this.years = years;
        this.index = index;
    }

    @Override
    public int getCount() {
        return years.size();
    }

    @Override
    public Object getItem(int i) {
        return years.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.select_year_item, null);
        if (view != null) {
            TextView itemTextView = (TextView) view.findViewById(R.id.year_item);
            if (itemTextView != null) {
                String year = years.get(i);
                itemTextView.setText(year);
            }
        }
        return view;
    }
}
