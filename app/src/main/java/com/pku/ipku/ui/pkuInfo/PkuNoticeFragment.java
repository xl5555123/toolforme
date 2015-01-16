package com.pku.ipku.ui.pkuInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pku.ipku.R;

public class PkuNoticeFragment extends Fragment {
    private ListView listView;
    public PkuNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_pku_notice, container, false);
        listView = (ListView)view.findViewById(R.id.notice_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_expandable_list_item_1,getData()));
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                expressItemClick(position,view);
            }
        };
        listView.setOnItemClickListener(listener);
        return view;
    }
    private void expressItemClick(int position,View view){

        getFragmentManager().beginTransaction()
                .replace(R.id.notice_fragment,new PkuNews_Lecture_NoticeWeb()).commit();

    }

    private String[] getData(){
        String[] s = new String[]{"notice1","notice2","notice3"};
        return s;
    }
}
