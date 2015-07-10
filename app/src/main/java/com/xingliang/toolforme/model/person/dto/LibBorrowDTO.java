package com.xingliang.toolforme.model.person.dto;

import java.util.Date;

/**
 * Created by Allen on 2015/1/19.
 */
public class LibBorrowDTO {

    private String bookName;
    private String bookSearchID;
    private String location;
    private String author;
    private Date borrowDate;
    private Date dueDate;

    public LibBorrowDTO() {

    }

    public LibBorrowDTO(String bookName, String bookSearchID, String location, String author, Date borrowDate, Date dueDate) {
        this.bookName = bookName;
        this.bookSearchID = bookSearchID;
        this.location = location;
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
