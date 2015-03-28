package com.pku.ipku.ui.person.freeClassRoom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class SelectDialogFragment extends DialogFragment {

    private String title;

    private List<String> itemToSelect;

    private String[] items;

    private boolean[] ifAssigned;

    private TextView textView;

    private List<String> result;

    private List<Integer> positionResult;

    public SelectDialogFragment() {
        super();
    }

    public SelectDialogFragment(String title, List<String> itemToSelect, TextView textViewToChange, List<String> result, List<Integer> postionResult) {
        this.title = title;
        this.itemToSelect = itemToSelect;
        this.textView = textViewToChange;
        this.result = result;
        this.positionResult = postionResult;
    }

    private String getTextAndSetResult() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < itemToSelect.size(); i++) {
            if (ifAssigned[i]) {
                builder.append(itemToSelect.get(i));
                builder.append(" ");
                result.add(itemToSelect.get(i));
                positionResult.add(i + 1);
            }
        }
        return builder.toString();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        items = new String[itemToSelect.size()];
        ifAssigned = new boolean[itemToSelect.size()];
        itemToSelect.toArray(items);
        final AlertDialog thisDialog = builder.setTitle(title)
                .setMultiChoiceItems(items, ifAssigned, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        ifAssigned[which] = isChecked;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        textView.setText(getTextAndSetResult());

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        textView.setText("无");
                        result.clear();
                    }
                }).create();
        return thisDialog;
    }
}
