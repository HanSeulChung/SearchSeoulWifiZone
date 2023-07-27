package com.example.mission1;

import java.sql.Timestamp;

public class BookMarkGroup {
    private int id;
    private String bookmarkName;
    private int order;
    private Timestamp regiDate;
    private Timestamp editDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Timestamp getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(Timestamp regiDate) {
        this.regiDate = regiDate;
    }

    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }
}
