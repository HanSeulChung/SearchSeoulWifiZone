package com.example.mission1.history;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class History {
    private int id;
    private double lat;
    private double lnt;
    private LocalDateTime inquiryDate;

    public History(int id, double lat, double lnt, LocalDateTime inquiryDate) {
        this.id = id;
        this.lat = lat;
        this.lnt = lnt;
        this.inquiryDate = inquiryDate;
    }

    // inquiryDate를 java.sql.Date로 변환하는 메서드
    public Date getInquiryDateAsSqlDate() {
        Timestamp timestamp = Timestamp.valueOf(this.inquiryDate);
        return new Date(timestamp.getTime());
    }
}
