package com.xingliang.toolforme.ui.person.queryClass;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.xingliang.toolforme.R;
import com.xingliang.toolforme.adapter.person.SelectYearAdapter;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectYearFragment extends Fragment {

    private ListView listView;
    private List<String> years;
    private Integer currentYear;

    private OnFinishSelectListener mListener;

    public SelectYearFragment() {
        // Required empty public constructor
        Calendar calendar = Calendar.getInstance();
        int currentYearDate = calendar.get(Calendar.YEAR);
        years = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            String currentYearString = String.format("%s-%s学年", currentYearDate - i - 1, currentYearDate - i);
            years.add(currentYearString);
        }
        years.add("全部");
        if (getArguments() != null) {
            currentYear = getArguments().getInt("selected_year");
        } else {
            currentYear = -1;
        }
    }

    public static SelectYearFragment newInstance(int index) {
        SelectYearFragment selectYearFragment = new SelectYearFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("selected_year", index);
        selectYearFragment.setArguments(bundle);
        return selectYearFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_year, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.year_listview);
        if (listView != null) {
            listView.setAdapter(new SelectYearAdapter(years, getActivity(), currentYear));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == -1) {
                        mListener.onFinishSelect("全部");
                    } else {
                        mListener.onFinishSelect(years.get(i));
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFinishSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFinishSelectListener {
        // TODO: Update argument type and name
        public void onFinishSelect(String year);
    }


}
