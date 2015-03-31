package com.pku.ipku.adapter.pkuInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;

import java.util.List;

/**
 * Created by XingLiang on 2015/3/4.
 */
public class PkuLectureAdapter extends BaseAdapter {

    private final List<PkuPublicInfo> pkuPublicInfoList;
    private final LayoutInflater listContainer;

    public PkuLectureAdapter(Context context, List<PkuPublicInfo> pkuPublicInfoList) {
        this.pkuPublicInfoList = pkuPublicInfoList;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return pkuPublicInfoList.size();
        //return 6;
    }

    @Override
    public PkuPublicInfo getItem(int position) {
        return pkuPublicInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = listContainer.inflate(R.layout.lecture_item, null);
        PkuPublicInfo pkuPublicInfo = getItem(position);

        TextView subject = (TextView) convertView.findViewById(R.id.title);
        subject.setText(pkuPublicInfo.getTitle());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(pkuPublicInfo.getPubDate());

        convertView.setTag(pkuPublicInfo.getLink());
        return convertView;
    }
}
