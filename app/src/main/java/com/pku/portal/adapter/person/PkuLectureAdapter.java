package com.pku.portal.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.portal.R;
import com.pku.portal.model.PubInfo;
import com.pku.portal.model.pkuInfo.dto.PkuPublicInfo;

import java.util.List;

/**
 * Created by XingLiang on 2015/3/4.
 */
public class PkuLectureAdapter extends BaseAdapter {

    private final List<PubInfo> pkuPublicInfoList;
    private final LayoutInflater listContainer;

    public PkuLectureAdapter(Context context, List<PubInfo> pkuPublicInfoList) {
        this.pkuPublicInfoList = pkuPublicInfoList;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        int len = pkuPublicInfoList.size();
        return len > 5 ? 5 : len;
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

        TextView subject = (TextView) convertView.findViewById(R.id.title_of_lecture);
        subject.setText(pkuPublicInfo.getTitle());

        TextView date = (TextView) convertView.findViewById(R.id.date_of_lecture);
        date.setText(pkuPublicInfo.getPubDate().substring(11));

        convertView.setTag(pkuPublicInfo.getLink());
        return convertView;
    }
}
