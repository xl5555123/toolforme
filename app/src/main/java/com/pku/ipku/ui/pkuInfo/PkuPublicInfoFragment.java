package com.pku.ipku.ui.pkuInfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.pkuInfo.PkuPublicAdapter;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.ui.util.WebViewActivity;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.DataHandleUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PkuPublicInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PkuPublicInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PkuPublicInfoFragment extends Fragment {
    private static final String TYPE = "type";
    private PkuInfoType pkuInfoType;
    private ListView listView;
    private List<PkuPublicInfo> pkuPublicInfoList;

    private OnFragmentInteractionListener mListener;
    // TODO: Rename and change types and number of parameters
    public static PkuPublicInfoFragment newInstance(String pkuInfoType) {
        PkuPublicInfoFragment fragment = new PkuPublicInfoFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, pkuInfoType);
        fragment.setArguments(args);
        return fragment;
    }

    public PkuPublicInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String pkuInfoTypeString = getArguments().getString(TYPE);
            if (pkuInfoTypeString != null) {
                pkuInfoType = new PkuInfoType();
                pkuInfoType.setType(pkuInfoTypeString);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pku_public_info, container, false);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.pku_pub_info_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String) view.getTag();
                if (url != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", "公共信息");
                    startActivity(intent);
                }
            }
        });
    }

    private void getData() {
        new LoadDataDefaultTask(new LoadDataConfigure() {
            @Override
            public void showData() {
                listView.setAdapter(new PkuPublicAdapter(AppContextHolder.getAppContext(), pkuPublicInfoList));
            }

            @Override
            public boolean getData(boolean cache) {
                pkuPublicInfoList = IpkuServiceFactory.getPkuInfoService(cache).getPkuPublicNotice(pkuInfoType, 0);
                if (pkuPublicInfoList == null) {
                    return false;
                }
                return true;
            }

            @Override
            public void showWaiting() {

            }

            @Override
            public void stopWaiting() {

            }
        }).execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
