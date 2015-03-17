package com.pku.ipku.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.collect.Maps;
import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.StuInfoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xingliang on 15-3-1.
 */
public class PersonInfoListAdapter extends BaseAdapter {

    private Map<String, String> items;
    private List<String> headers;
    private LayoutInflater layoutInflater;

    public PersonInfoListAdapter(Context context, StuInfoDTO stuInfoDTO) {
        layoutInflater = LayoutInflater.from(context);
        items = Maps.newHashMap();
        items.put("名字", stuInfoDTO.getName());
        items.put("名字简写", stuInfoDTO.getNameAbbr());
        items.put("身份", stuInfoDTO.getUserIdentity());
        items.put("性别", stuInfoDTO.getSex());
        items.put("民族", stuInfoDTO.getEthnic());
        items.put("籍贯", stuInfoDTO.getNativePlace());
        items.put("政治面貌", stuInfoDTO.getPolitics());
        items.put("出生日期", stuInfoDTO.getBirthDate());
        items.put("入学年份", stuInfoDTO.getEnrollDate());
        items.put("学号", stuInfoDTO.getStudentId());
        items.put("所在院系", stuInfoDTO.getDepartment());
        items.put("专业", stuInfoDTO.getSpeciality());
        items.put("研究方向", stuInfoDTO.getDirection());
        items.put("导师", stuInfoDTO.getTutor());
        items.put("学生类别", stuInfoDTO.getStudentType());
        headers = new ArrayList<String>(items.keySet());
    }

    @Override
    public int getCount() {
        return items.keySet().size();
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
        convertView = layoutInflater.inflate(R.layout.person_info_item, null);
        String header = headers.get(position);
        String result = items.get(header);
        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        if (titleView != null) {
            titleView.setText(header);
        }
        TextView resultView = (TextView) convertView.findViewById(R.id.result);
        if (resultView != null) {
            resultView.setText(result);
        }
        return convertView;
    }
}
