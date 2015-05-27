package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.model.person.dto.ScholarShipDTO;

import java.util.List;

/**
 * Created by Allen on 2015/1/22.
 */
public class ScholarshipAdapter extends BaseAdapter {
    private final LayoutInflater listContainer;// 视图容器
    private List<ScholarShipDTO> scholarshipDTOs;
    private Context context;

    public ScholarshipAdapter(Context context, List<ScholarShipDTO> scholarshipDTOs) {
        this.scholarshipDTOs = scholarshipDTOs;
        this.context = context;
        this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
    }

    @Override
    public int getCount() {
        return scholarshipDTOs.size();
    }

    @Override
    public Object getItem(int i) {
        return scholarshipDTOs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ScholarShipDTO scholarShip = scholarshipDTOs.get(i);
        view = listContainer.inflate(R.layout.scholarship_item, null);

        TextView scholarshipName = (TextView) view.findViewById(R.id.scholarship_name);
        TextView scholarshipTime = (TextView) view.findViewById(R.id.scholarship_time);
        TextView scholarshipMoney = (TextView) view.findViewById(R.id.scholarship_money);

        scholarshipName.setText(scholarShip.getScholarshipName());
        scholarshipTime.setText(scholarShip.getSemester());
        scholarshipMoney.setText(scholarShip.getMoney() + "");

        return view;
    }
}
