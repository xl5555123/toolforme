package com.xingliang.toolforme.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.collect.Sets;
import com.xingliang.toolforme.R;
import com.xingliang.toolforme.model.PubInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by XingLiang on 2015/1/30.
 */
public class PkuPublicAdapter extends BaseAdapter {

    private final List<PubInfo> datas;
    private final LayoutInflater listContainer;
    private final Set<String> titleSet;

    public PkuPublicAdapter(Context context, List<PubInfo> datas) {
        this.listContainer = LayoutInflater.from(context);
        this.datas = datas;
        titleSet = Sets.newHashSet();
        if (datas != null) {
            for (PubInfo pubInfo : datas) {
                if (pubInfo.isCollected()) {
                    titleSet.add(pubInfo.getTitle());
                }
            }
        }
        Iterator<PubInfo> pubInfoIterator = datas.iterator();
        while (pubInfoIterator.hasNext()) {
            PubInfo pkuPublicInfo = pubInfoIterator.next();
            if (!pkuPublicInfo.isCollected() && titleSet.contains(pkuPublicInfo.getTitle())) {
                pubInfoIterator.remove();
            }
        }
    }

    public void addData(List<PubInfo> newDatas) {
        Iterator<PubInfo> pubInfoIterator = newDatas.iterator();
        while (pubInfoIterator.hasNext()) {
            PubInfo pubInfo = pubInfoIterator.next();
            if (titleSet.contains(pubInfo.getTitle())) {
                pubInfoIterator.remove();
            }
        }
        datas.addAll(newDatas);
    }


    public void addCollectData(PubInfo data) {
        datas.add(0, data);
        Iterator<PubInfo> pubInfoIterator = datas.iterator();
        titleSet.add(data.getTitle());
        while (pubInfoIterator.hasNext()) {
            PubInfo pkuPublicInfo = pubInfoIterator.next();
            if (!pkuPublicInfo.isCollected() && pkuPublicInfo.getTitle().equals(data.getTitle())) {
                pubInfoIterator.remove();
            }
        }
        notifyDataSetChanged();
    }

    public void removeCollectData(PubInfo data) {
        Iterator<PubInfo> pubInfoIterator = datas.iterator();
        while (pubInfoIterator.hasNext()) {
            PubInfo pkuPublicInfo = pubInfoIterator.next();
            if (pkuPublicInfo.isCollected() && pkuPublicInfo.getTitle().equals(data.getTitle())) {
                pkuPublicInfo.setCollected(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public PubInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = listContainer.inflate(R.layout.pku_public_info_item, null);
        PubInfo pkuPublicInfo = getItem(position);

        TextView subject = (TextView) convertView.findViewById(R.id.title);
        subject.setText(pkuPublicInfo.getTitle());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(pkuPublicInfo.getPubDate());

        convertView.setTag(pkuPublicInfo);
        if (pkuPublicInfo.isCollected()) {
            convertView.findViewById(R.id.collected).setVisibility(View.VISIBLE);
        }
        else {
            convertView.findViewById(R.id.collected).setVisibility(View.GONE);
        }
        return convertView;
    }
}
