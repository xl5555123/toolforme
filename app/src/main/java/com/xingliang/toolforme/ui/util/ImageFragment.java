package com.xingliang.toolforme.ui.util;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingliang.toolforme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(int imageId) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt("imageId", imageId);
        imageFragment.setArguments(args);
        return imageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int imageId = getArguments().getInt("imageId", 0);
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        if (imageId != 0) {
            ImageView imageView = (ImageView) view.findViewById(R.id.image_banner);
            imageView.setBackground(getResources().getDrawable(imageId));
        }
        return view;
    }


}
