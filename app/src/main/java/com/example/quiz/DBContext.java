package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBContext extends SQLiteOpenHelper {
    public DBContext(@Nullable Context context){
        super(context, "counter1.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String recordTable = "CREATE TABLE " + "TASBEEH_RECORD" + " (" +
                " DATE TEXT," +
                " KALMA Integer," +
                " DROODSHAREEF Integer," +
                " ASTAGFAR Integer" +
                ");";
        sqLiteDatabase.execSQL(recordTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS TASBEEH_RECORD ";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    public void addTasbeeh(Tasbeeh tasbeeh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATE", tasbeeh.getDate());
        cv.put("KALMA", tasbeeh.getKalma());
        cv.put("DROODSHAREEF", tasbeeh.getDroodshreef());
        cv.put("ASTAGFAR", tasbeeh.getAstagfaar());
        if (db.insert("TASBEEH_RECORD", null, cv) == -1){
            db.close();
        }
    }

    public void updateTasbeeh(Tasbeeh tasbeeh) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("KALMA", tasbeeh.getKalma());
        values.put("DROODSHAREEF", tasbeeh.getDroodshreef());
        values.put("ASTAGFAR", tasbeeh.getAstagfaar());
        db.update("TASBEEH_RECORD", values, "DATE = ?", new String[]{tasbeeh.getDate()});
        db.close();
    }

    public ArrayList<Tasbeeh> getAllTasbeeh() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("Select * from "+ "TASBEEH_RECORD",null);
            ArrayList<Tasbeeh> all_tasbeeh = new ArrayList<>();
            if (cr.moveToFirst()) {
                do {
                    all_tasbeeh.add(new Tasbeeh(cr.getString(0), cr.getInt(1), cr.getInt(2), cr.getInt(3)));
                } while (cr.moveToNext());
            }
            return all_tasbeeh;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Tasbeeh> get_one(String date) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("Select * from "+ "TASBEEH_RECORD" +" WHERE DATE = "+ date + " ORDER BY DATE DESC",null);
            ArrayList<Tasbeeh> record = new ArrayList<>();
            if (cr.moveToFirst()) {
                do {
                    record.add(new Tasbeeh(cr.getString(0),
                            cr.getInt(1),
                            cr.getInt(2),
                            cr.getInt(3)));
                } while (cr.moveToNext());
            }
            return record;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ArrayList<String> getDate(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = db.rawQuery("select CurrentDate from days", null);
        while(c.moveToNext()){
            result.add(c.getString(0));
        }
        return result;
    }

    public void addDate(String date){

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

