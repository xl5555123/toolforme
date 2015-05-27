package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pku.portal.R;
import com.pku.portal.model.person.dto.StuInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by xingliang on 15-3-1.
 */
public class PersonInfoListAdapter extends BaseAdapter {

    private Map<String, String> items;
    private List<String> headers;
    private LayoutInflater layoutInflater;

    public PersonInfoListAdapter(Context context, StuInfoDTO stuInfoDTO, List<String> keyOrder) {
        layoutInflater = LayoutInflater.from(context);
        items = Maps.newHashMap();
        items.put("姓名", stuInfoDTO.getName());
        items.put("名字简写", stuInfoDTO.getNameAbbr());
        items.put("身份", stuInfoDTO.getUserIdentity());
        items.put("性别", stuInfoDTO.getSex());
        items.put("民族", stuInfoDTO.getEthnic());
        items.put("籍贯", stuInfoDTO.getNativePlace());
        items.put("政治面貌", stuInfoDTO.getPolitics());
        items.put("出生日期", stuInfoDTO.getBirthDate());
        items.put("入学年份", stuInfoDTO.getEnrollDate());
        items.put("学号", stuInfoDTO.getStudentId());
        items.put("职工号", stuInfoDTO.getStudentId());
        items.put("所在院系", stuInfoDTO.getDepartment());
        items.put("所在单位", stuInfoDTO.getDepartment());
        items.put("专业", stuInfoDTO.getSpeciality());
        items.put("研究方向", stuInfoDTO.getDirection());
        items.put("导师", stuInfoDTO.getTutor());
        items.put("学生类别", stuInfoDTO.getStudentType());
        headers = Lists.newArrayList(keyOrder);
        if (stuInfoDTO.getUserIdentity() != null && !stuInfoDTO.getUserIdentity().equals("学生")) {
            headers.remove("导师");
            headers.remove("研究方向");
            headers.remove("学生类别");
            headers.remove("入学年份");
            headers.remove("所在院系");
            headers.remove("学号");
            headers.add(2, "职工号");
            headers.add(3, "所在单位");
        }
    }

    @Override
    public int getCount() {
        return headers.size();
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
