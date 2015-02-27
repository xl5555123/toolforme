package com.pku.ipku.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.LibBorrowDTO;

import java.util.List;

/**
 * Created by Allen on 2015/1/22.
 */
public class LibStateAdapter extends BaseAdapter {

    private final List<LibBorrowDTO> bookList;
    private final LayoutInflater listContainer;// 视图容器
    private Context context;

    public LibStateAdapter(Context context, List<LibBorrowDTO> bookList) {
        this.context = context;
        this.bookList = bookList;
        this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LibBorrowDTO book = bookList.get(i);
        view = listContainer.inflate(R.layout.lib_book_item, null);
        TextView bookName = (TextView) view.findViewById(R.id.lib_book_name);
        TextView authorName = (TextView) view.findViewById(R.id.lib_book_author_name);
        TextView bookSearchID = (TextView) view.findViewById(R.id.lib_book_search_id);
        TextView borrowTime = (TextView) view.findViewById(R.id.lib_book_borrow_date);
        TextView dueTime = (TextView) view.findViewById(R.id.lib_book_due_date);

        bookName.setText(book.getBookName());
        authorName.setText(book.getAuthor());
        bookSearchID.setText(book.getBookSearchID());
        borrowTime.setText(book.getBorrowDate());
        dueTime.setText(book.getDueDate());

        return view;
    }
}
