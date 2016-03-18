package com.u3coding.dashboard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyProgressBar progressBar = (MyProgressBar)findViewById(R.id.progress);
        progressBar.setProgressColor(Color.RED);
        progressBar.setProgressWidth(10);
        progressBar.start(3);

    }

}
