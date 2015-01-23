package com.pku.ipku.model.person.dto;

/**
 * Created by Allen on 2015/1/19.
 */
public class LibBorrowDTO {

    private String bookName;
    private String bookSearchID;
    private String author;
    private String borrowDate;
    private String dueDate;

    public LibBorrowDTO(){

    }

    public LibBorrowDTO(String bookName, String bookSearchID, String author, String borrowDate, String dueDate) {
        this.bookName = bookName;
        this.bookSearchID = bookSearchID;
        this.author = author;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSearchID() {
        return bookSearchID;
    }

    public void setBookSearchID(String bookSearchID) {
        this.bookSearchID = bookSearchID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
