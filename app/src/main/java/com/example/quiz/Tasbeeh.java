package com.example.quiz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tasbeeh {
    private String date;
    private Integer kalma;
    private Integer droodshreef;
    private Integer astagfaar;

    public Tasbeeh(Integer kalma, Integer droodshreef, Integer astagfaar) {
        this.date = getDateToString();
        this.kalma = kalma;
        this.droodshreef = droodshreef;
        this.astagfaar = astagfaar;
    }

    public Tasbeeh(String date, Integer kalma, Integer droodshreef, Integer astagfaar) {
        this.date = date;
        this.kalma = kalma;
        this.droodshreef = droodshreef;
        this.astagfaar = astagfaar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getKalma() {
        return kalma;
    }

    public void setKalma(Integer kalma) {
        this.kalma = kalma;
    }

    public Integer getDroodshreef() {
        return droodshreef;
    }

    public void setDroodshreef(Integer droodshreef) {
        this.droodshreef = droodshreef;
    }

    public Integer getAstagfaar() {
        return astagfaar;
    }

    public void setAstagfaar(Integer astagfaar) {
        this.astagfaar = astagfaar;
    }

    public static String getDateToString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String dateToString = df.format(today);
        return dateToString;
    }

    @Override
    public String toString() {
        return (date);
    }
}
