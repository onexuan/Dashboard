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
        progressBar.setProgressColor(getResources().getColor(R.color.progress));
        progressBar.setProgressBackColor(getResources().getColor(R.color.progressBack));
        progressBar.setDrawable(getResources().getDrawable(R.drawable.casdf));
        progressBar.setBoundWidth(0);
        progressBar.setProgressWidth(30);
        progressBar.setIsRote(true);
        progressBar.start(3);

    }

}
