package com.pku.ipku.adapter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.setting.Telephone;

import java.util.List;

/**
 * Created by XingLiang on 2015/3/31.
 */
public class PhoneAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<Telephone> phoneList;

    public PhoneAdapter(Context context, List<Telephone> telephones) {
        layoutInflater = LayoutInflater.from(context);
        this.phoneList = telephones;
    }

    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int position) {
        return phoneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.phone_item, null);
        Telephone phone = phoneList.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.department_name);
        name.setText(phone.getDepartment());
        TextView number = (TextView) convertView.findViewById(R.id.phone_number);
        number.setText(phone.getTelephone());
        return convertView;
    }
}
