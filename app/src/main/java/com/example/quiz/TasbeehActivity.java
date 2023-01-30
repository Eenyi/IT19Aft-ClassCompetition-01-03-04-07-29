package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TasbeehActivity extends AppCompatActivity {

    Button btn;
    ListView lst;
    LocalDate date;
    DateTimeFormatter df;
    SimpleDateFormat ff;
    DateFormat dd;
    ArrayList<String> dates;
    String dateF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh);
        lst = findViewById(R.id.dayslist);
        btn = findViewById(R.id.btnwithnoeffort);
        DBContext db = new DBContext(this);
        dates = db.getDate();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(TasbeehActivity.this, android.R.layout.simple_list_item_1, dates);
        lst.setAdapter(arrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dd = new SimpleDateFormat("dd/MM/yyyy");
                Date today = Calendar.getInstance().getTime();
                dateF = dd.format(today);
                if (!dates.contains(dateF)) {
                    //databse
                    dates.add(dateF);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String def = (String) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(TasbeehActivity.this, MainActivity.class);
                intent.putExtra("Date", def);
                startActivity(intent);


            }
        });
    }
}