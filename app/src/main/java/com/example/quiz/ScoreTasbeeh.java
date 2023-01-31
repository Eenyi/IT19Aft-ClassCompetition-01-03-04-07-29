package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreTasbeeh extends AppCompatActivity {
    Intent intent;
    TextView kalma, drood_sh, astagfar;
    ImageView k, d, a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_tasbeeh);
        intent = getIntent();
        kalma = findViewById(R.id.kalma_count);
        kalma.setText(intent.getStringExtra("KALMA"));
        drood_sh = findViewById(R.id.drood_count);
        drood_sh.setText(intent.getStringExtra("DROODSHAREEF"));
        astagfar = findViewById(R.id.astagfar_count);
        astagfar.setText(intent.getStringExtra("ASTAGFAR"));
        //setting flags
        k = findViewById(R.id.kalmaFlag);
        d = findViewById(R.id.droodFlag);
        a = findViewById(R.id.AstagfaarFlag);
//        if (intent.getStringExtra("KALMA").equals(0)) {
//            k.setImageResource(R.drawable.ic_baseline_block_24);
//        }
//        if (intent.getStringExtra("DROODSHAREEF").equals(0)) {
//            d.setImageResource(R.drawable.ic_baseline_block_24);
//        }
//        if (intent.getStringExtra("ASTAGFAR").equals(0)) {
//            a.setImageResource(R.drawable.ic_baseline_block_24);
//        }
    }
}