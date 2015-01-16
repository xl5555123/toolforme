package com.pku.ipku.adapter.pkuInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;

import java.util.List;

/**
 * Created by Allen on 2015/1/9.
 */
public class PkuJobAdapter extends BaseAdapter {

    private final List<PkuJobDTO> pkuJobList;
    private final LayoutInflater listContainer;// 视图容器
    private Context context;

    public PkuJobAdapter(Context context,List<PkuJobDTO> pkuJobList) {
        this.pkuJobList = pkuJobList;
        this.context = context;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pkuJobList.size();
    }

    @Override
    public Object getItem(int i) {
        return pkuJobList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = listContainer.inflate(R.layout.job_item, null);
        final PkuJobDTO pkuJobDTO = pkuJobList.get(i);
        TextView subject = (TextView)view.findViewById(R.id.job_subject);
        subject.setText("["+pkuJobDTO.getType()+"]"+pkuJobDTO.getSubject());
        view.setTag(pkuJobDTO.getAttachUrl());
        return view;
    }
}
