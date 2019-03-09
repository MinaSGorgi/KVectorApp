package com.example.KVectorApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MagazineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.tv_magazine);
        textView.setText(intent.getStringExtra("body"));
    }
}
