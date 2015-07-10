package com.xingliang.toolforme.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.model.person.dto.LibBorrowDTO;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xingliang on 15-2-27.
 */
public class MyLendAdapter extends BaseAdapter {

    private List<LibBorrowDTO> libBorrowDTOList;

    private LayoutInflater inflater;

    public MyLendAdapter(Context context, List<LibBorrowDTO> libBorrowDTOList) {
        this.libBorrowDTOList = libBorrowDTOList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return libBorrowDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return libBorrowDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_lend_list_item, null);
        LibBorrowDTO libBorrowDTO = libBorrowDTOList.get(position);

        TextView bookNameTextView = (TextView) convertView.findViewById(R.id.book_name);
        if (bookNameTextView != null) {
            bookNameTextView.setText(libBorrowDTO.getBookName());
        }

        TextView bookLocationTextView = (TextView) convertView.findViewById(R.id.book_location);
        if (bookLocationTextView != null) {
            bookLocationTextView.setText(libBorrowDTO.getLocation());
        }

        TextView bookDateTextView = (TextView) convertView.findViewById(R.id.book_date);
        if (bookDateTextView != null) {
            bookDateTextView.setText(DateFormat.getDateInstance().format(libBorrowDTO.getDueDate()));
        }

        TextView bookExpiredTextView = (TextView) convertView.findViewById(R.id.book_expired);
        if (bookExpiredTextView != null) {
            Date dueDate = libBorrowDTO.getDueDate();
            if (dueDate.after(new Date())) {
                bookExpiredTextView.setText("已过期");
            } else {
                bookDateTextView.setText("未过期");
            }
        }
        return convertView;
    }
}
