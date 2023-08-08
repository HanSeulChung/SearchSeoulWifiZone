package com.example.mission1;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BookMark {
    private int id;
    private String bookmarkName;
    private String wifiName;
    private Timestamp regiDate;
}
