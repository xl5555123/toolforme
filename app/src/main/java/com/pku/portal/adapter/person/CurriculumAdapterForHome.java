package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.model.person.dto.CurriculumDTO;

import java.util.List;

/**
 * Created by vector liu on 2015/3/21.
 */
public class CurriculumAdapterForHome extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    List<CurriculumDTO> curriculums;

    public CurriculumAdapterForHome(Context context, List<CurriculumDTO> curriculums) {
        this.context = context;
        this.curriculums = curriculums;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean hasStableIds() {
        return super.hasStableIds();
    }

    @Override
    public int getCount() {
        return curriculums.size();
    }

    @Override
    public Object getItem(int position) {
        return curriculums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder == null) {
            convertView = layoutInflater.inflate(R.layout.curriculum_item_for_home, null);
            holder = new ViewHolder();
            holder.course_name_tv = (TextView) convertView.findViewById(R.id.course_name_tv);
            holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CurriculumDTO curriculum = curriculums.get(position);
        curriculum.getTime();
        String detailTime = curriculum.getDetailTime();
        String course_name = curriculum.getCourseName();
        String time_num = curriculum.getTimeNum();
        String room_name = curriculum.getRoomName();
        String parity = curriculum.getParity();
        holder.course_name_tv.setText(course_name);
        holder.time_tv.setText(detailTime);

        return convertView;
    }

    static class ViewHolder {
        TextView course_name_tv;
        TextView time_tv;
    }
}
