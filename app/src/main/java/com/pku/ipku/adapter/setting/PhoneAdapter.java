package com.pku.ipku.adapter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.setting.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingLiang on 2015/3/31.
 */
public class PhoneAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<Phone> phoneList = new ArrayList<Phone>() {
        {
            add(new Phone("数学科学学院", "62751804"));
            add(new Phone("物理学院", "62751732"));
            add(new Phone("化学与分子工程学院", "62751710"));
            add(new Phone("生命科学学院", "62751840"));
            add(new Phone("城市与环境学院", "62751172"));
            add(new Phone("地球与空间科学学院", "62751150"));
            add(new Phone("心理学系", "62751831"));
            add(new Phone("建筑与景观设计学院", "62759003"));
            add(new Phone("信息科学学院", "62751760"));
            add(new Phone("工学院", "62751812"));
        }
    };

    public PhoneAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
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
        Phone phone = phoneList.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.department_name);
        name.setText(phone.getDepartmentName());
        TextView number = (TextView) convertView.findViewById(R.id.phone_number);
        number.setText(phone.getPhoneNumber());
        return convertView;
    }
}
