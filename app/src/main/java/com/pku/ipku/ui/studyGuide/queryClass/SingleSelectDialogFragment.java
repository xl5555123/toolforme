package com.pku.ipku.ui.studyGuide.queryClass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class SingleSelectDialogFragment extends DialogFragment {

    private String title;

    private List<String> itemToSelect;

    private String[] items;

    private TextView textView;

    private String result;

    public SingleSelectDialogFragment() {
        super();
    }

    public SingleSelectDialogFragment(String title, List<String> itemToSelect, TextView textViewToChange, String result) {
        this.title = title;
        this.itemToSelect = itemToSelect;
        this.textView = textViewToChange;
        this.result = result;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        items = new String[itemToSelect.size()];
        itemToSelect.toArray(items);
        final AlertDialog thisDialog = builder.setTitle(title)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = items[which];
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        textView.setText(result);

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        textView.setText("无");
                        result = null;
                    }
                }).create();
        return thisDialog;
    }
}
