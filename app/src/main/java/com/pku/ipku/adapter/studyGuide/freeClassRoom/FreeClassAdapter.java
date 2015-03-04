package com.pku.ipku.adapter.studyGuide.freeClassRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pku.ipku.R;
import com.pku.ipku.util.AppContextHolder;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class FreeClassAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter {

    private final LayoutInflater mInflater;
    private List<ClassRoom> classRooms;

    public FreeClassAdapter(Map<String, List<Integer>> classroomNumberMap) {
        mInflater = LayoutInflater.from(AppContextHolder.getAppContext().getApplicationContext());
        this.classRooms = Lists.newArrayList();
        for (String buildingName : classroomNumberMap.keySet()) {
            for (int classNum : classroomNumberMap.get(buildingName)) {
                classRooms.add(new ClassRoom(classNum, buildingName));
            }
        }
    }

    @Override
    public long getHeaderId(int position) {
        return classRooms.get(position).buidingName.charAt(0);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.free_class_header, parent, false);
        TextView headView = (TextView) convertView.findViewById(R.id.group_name);
        headView.setText(classRooms.get(position).buidingName);

        return convertView;
    }

    @Override
    public int getCount() {
        return classRooms.size();
    }

    @Override
    public ClassRoom getItem(int i) {
        return classRooms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView itemView;
        view = mInflater.inflate(R.layout.free_class_item, viewGroup, false);
        itemView = (TextView) view.findViewById(R.id.classroom_number);
        final ClassRoom item = getItem(i);
        itemView.setText(String.format("%d", classRooms.get(i).classroomNumber));

        return view;
    }

    class ClassRoom {
        final int classroomNumber;
        final String buidingName;

        ClassRoom(int classroomNumber, String buidingName) {
            this.classroomNumber = classroomNumber;
            this.buidingName = buidingName;
        }
    }
}
