package com.pku.ipku.ui.person.library;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibrarySearchListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibrarySearchListFragment extends Fragment {

    private static final String TYPE = "tyoe";
    private static final String QUERY = "query";

    private String type;
    private String query;

    public LibrarySearchListFragment() {
        // Required empty public constructor
    }

    public static LibrarySearchListFragment newInstance(String type, String query) {
        LibrarySearchListFragment fragment = new LibrarySearchListFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        args.putString(QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
            query = getArguments().getString(QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_search_list, container, false);

        return view;
    }

    private void initView() {

    }


}
