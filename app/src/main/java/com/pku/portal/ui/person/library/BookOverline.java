package com.pku.portal.ui.person.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.portal.R;

public class BookOverline extends Fragment {
    public static BookOverline newInstance() {
        BookOverline fragment = new BookOverline();
        return fragment;
    }

    public BookOverline() {
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
        return inflater.inflate(R.layout.fragment_book_overline, container, false);
    }
}
