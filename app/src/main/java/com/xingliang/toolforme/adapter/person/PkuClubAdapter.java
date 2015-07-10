package com.xingliang.toolforme.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.model.pkuInfo.dto.PkuClubDTO;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;
import java.util.Locale;

/**
 * Created by Allen on 2015/1/7.
 */
public class PkuClubAdapter extends BaseAdapter {

    private final List<PkuClubDTO> pkuClubList;
    private final LayoutInflater listContainer;// 视图容器
    private Context context;

    public PkuClubAdapter(Context context, List<PkuClubDTO> pkuClubList) {
        this.pkuClubList = pkuClubList;
        this.context = context;
        this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
    }

    @Override
    public int getCount() {
        return pkuClubList.size();
    }

    @Override
    public Object getItem(int i) {
        return pkuClubList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final PkuClubDTO pkuClubDTO = pkuClubList.get(i);
        view = listContainer.inflate(R.layout.club_item, null);

        TextView subjectView = (TextView) view.findViewById(R.id.club_activity_subject);
        TextView clubNameView = (TextView) view.findViewById(R.id.club_activity_clubname);
        TextView locationView = (TextView) view.findViewById(R.id.club_activity_location);
        TextView startTimeView = (TextView) view.findViewById(R.id.club_activity_start_date);
        TextView createTimeView = (TextView) view.findViewById(R.id.club_activity_create_date);

        subjectView.setText(pkuClubDTO.getSubject());
        clubNameView.setText(pkuClubDTO.getClubName());
        locationView.setText(pkuClubDTO.getLocation());
        startTimeView.setText(new PrettyTime(new Locale("zh")).format(pkuClubDTO.getStartTime()));
        createTimeView.setText(new PrettyTime(new Locale("zh")).format(pkuClubDTO.getCreateTime()));
        view.setTag(pkuClubDTO.getAttachUrl());
        return view;
    }
}