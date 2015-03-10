package com.pku.ipku.ui.person.arrearageState;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;

public class ArrearageStateFragment extends Fragment {

    // TODO: Rename and change types and number of parameters
    public static ArrearageStateFragment newInstance() {
        ArrearageStateFragment fragment = new ArrearageStateFragment();

        return fragment;
    }

    public ArrearageStateFragment() {
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
        View view = inflater.inflate(R.layout.fragment_arrearage_state, container, false);

        return view;
    }

}
