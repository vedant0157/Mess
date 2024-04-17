package com.example.messfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class game extends AppCompatActivity {
    String name1,name2,name3,name4;
    TextView nm1textview ,nm2textview ,ct1textview ,ct2textview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent I = getIntent();
        name1 = I.getStringExtra("name_key1");
        name2 = I.getStringExtra("name_key2");
        name3 = I.getStringExtra("reg_key1");
        name4 = I.getStringExtra("reg_key2");
        nm1textview = findViewById(R.id.textView1);
        nm1textview.setText(name1);
        nm2textview = findViewById(R.id.textView2);
        nm2textview.setText(name2);
        ct1textview = findViewById(R.id.textView3);
        ct1textview.setText(name3);
        ct2textview = findViewById(R.id.textView4);
        ct2textview.setText(name4);

    }
}