package com.example.mission1.history;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class History {
    private int id;
    private double lat;
    private double lnt;
    private Date inquiryDate;
}
