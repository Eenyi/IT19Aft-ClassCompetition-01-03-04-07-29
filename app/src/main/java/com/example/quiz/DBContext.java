package com.example.quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(@Nullable Context context){
        super(context, "counter", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creation = "create table if not exists days(" +
                "ID integer primary key autoincrement,"+
                "Tareekh varchar,"+
                "QalmaFlag bool,"+
                "QalmaCount integer,"+
                "DaroodFlag bool,"+
                "DaroodCount integer,"+
                "AstagfarFlag bool,"+
                "AstagfarCount integer)";
        sqLiteDatabase.execSQL(creation);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public ArrayList<String> getDate(){
       String insert = "insert into days(tareekh, qalmaflag, qalmacount, daroodflag, daroodcount, astagfarflag, astagfarcount) values('30/10/2023',1,2,0,0,1,3)";

        SQLiteDatabase s = this.getWritableDatabase();
        s.execSQL(insert);
        String query = "select Tareekh from days";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()){
            result.add(c.getString(0));
        }
        return result;
    }
    public ArrayList<String> getRecord(String date){
        String query = "select * from days where tareekh = " + date;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        result.add(Integer.toString(c.getInt(1)));
        result.add(Integer.toString(c.getInt(2)));
        result.add(Integer.toString(c.getInt(3)));
        result.add(Integer.toString(c.getInt(4)));
        result.add(Integer.toString(c.getInt(5)));
        result.add(Integer.toString(c.getInt(6)));
        return result;
    }

    public ArrayList<Integer> getPerStudentRecords(String date) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from days where tareekh = " + date,null);
            ArrayList<Integer> result = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    result.add(c.getInt(1));
                    result.add(c.getInt(2));
                    result.add(c.getInt(3));
                    result.add(c.getInt(4));
                    result.add(c.getInt(5));
                    result.add(c.getInt(6));
                } while (c.moveToNext());
            }
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }
}

