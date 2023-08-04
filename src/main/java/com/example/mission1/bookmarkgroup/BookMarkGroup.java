package com.example.mission1.bookmarkgroup;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BookMarkGroup {
    private int id;
    private String bookmarkName;
    private int order;
    private Timestamp regiDate;
    private Timestamp editDate;

}
