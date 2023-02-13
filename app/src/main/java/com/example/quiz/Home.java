package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {
    Button btn;
    ListView lst;
    LocalDate date;
    DateTimeFormatter df;
    SimpleDateFormat ff;
    DateFormat dd;
    ArrayList<Tasbeeh> dates;
    String dateF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lst = findViewById(R.id.date_list);
        btn = findViewById(R.id.add_date);
        DBContext db = new DBContext(this);
        dates = db.getAllTasbeeh();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(Home.this, android.R.layout.simple_list_item_1, dates);
        lst.setAdapter(arrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder addStudent_dialogue = new AlertDialog.Builder(Home.this);
                LayoutInflater lf = getLayoutInflater();
                View layout = lf.inflate(R.layout.tasbeeh_layout, (ViewGroup) findViewById(R.id.t_l));
                addStudent_dialogue.setView(layout);
                final Button k_btn = (Button) layout.findViewById(R.id.k_btn);
                final Button d_btn = (Button) layout.findViewById(R.id.d_btn);
                final Button a_btn = (Button) layout.findViewById(R.id.a_btn);
                final TextView a_count = (TextView) layout.findViewById(R.id.a_count);
                final TextView d_count = (TextView) layout.findViewById(R.id.d_count);
                final TextView k_count = (TextView) layout.findViewById(R.id.k_count);
                k_btn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        k_count.setText(String.valueOf(Integer.parseInt(k_count.getText().toString())+1));
                    }
                });
                d_btn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        d_count.setText(String.valueOf(Integer.parseInt(d_count.getText().toString())+1));
                    }
                });
                a_btn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        a_count.setText(String.valueOf(Integer.parseInt(a_count.getText().toString())+1));
                    }
                });
                addStudent_dialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Tasbeeh tasbeeh = new Tasbeeh(Integer.parseInt(k_count.getText().toString()),
                                Integer.parseInt(d_count.getText().toString()),
                                Integer.parseInt(a_count.getText().toString()));
                        boolean updateStatus = false;
                        for (Tasbeeh temp: dates) {
                            if (tasbeeh.getDate().equals(temp.getDate())) {
                                tasbeeh.setKalma(Integer.parseInt(k_count.getText().toString())+temp.getKalma());
                                tasbeeh.setDroodshreef(Integer.parseInt(d_count.getText().toString())+temp.getDroodshreef());
                                tasbeeh.setAstagfaar(Integer.parseInt(a_count.getText().toString())+temp.getAstagfaar());
                                db.updateTasbeeh(tasbeeh);
                                updateStatus = true;
                            }
                        }
                        if (!updateStatus) {
                            db.addTasbeeh(tasbeeh);
                        }
                        arrayAdapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                addStudent_dialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
                });
                addStudent_dialogue.show();
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tasbeeh def = (Tasbeeh) adapterView.getAdapter().getItem(i);
                Log.d("Tasbeeh Data",def.getDate());
                Log.d("Tasbeeh Data",def.getAstagfaar().toString());
                Log.d("Tasbeeh Data",def.getDroodshreef().toString());
                Log.d("Tasbeeh Data",def.getKalma().toString());
                Intent intent = new Intent(Home.this, ScoreTasbeeh.class);
                intent.putExtra("DATE", def.getDate());
                intent.putExtra("KALMA",def.getKalma().toString());
                intent.putExtra("DROODSHAREEF", def.getDroodshreef().toString());
                intent.putExtra("ASTAGFAR", def.getAstagfaar().toString());
                startActivity(intent);
            }
        });
    }
}