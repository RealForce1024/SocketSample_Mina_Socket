package com.jahx.protocol;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fqc on 15/8/11.
 */
@Embeddable
public class DateEntity implements Serializable{

    private String year;
    private String mon;
    private String date;
    private String hr;
    private String min;
    private String sec;



    public DateEntity() {
    }

   /* map.put("year", year);
    map.put("month", month);
    map.put("day", day);
    map.put("hour", hour);
    map.put("minute", minute);
    map.put("second", second);*/
    public DateEntity(Map map){
        this.year = (String)map.get("year");
        this.mon = (String) map.get("month");
        this.date = (String) map.get("day");
        this.hr = (String) map.get("hour");
        this.min = (String) map.get("minute");
        this.sec = (String) map.get("second");
    }

    public DateEntity(String year, String mon, String date, String hr, String min, String sec) {
        this.year = year;
        this.mon = mon;
        this.date = date;
        this.hr = hr;
        this.min = min;
        this.sec = sec;
    }

    @Override
    public String toString() {
        return "DateEntity{" +
                "year=" + year +
                ", mon=" + mon +
                ", date=" + date +
                ", hr=" + hr +
                ", min=" + min +
                ", sec=" + sec +
                '}';
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }
}
