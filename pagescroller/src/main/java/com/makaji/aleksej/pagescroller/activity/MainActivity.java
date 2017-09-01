package com.makaji.aleksej.pagescroller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.makaji.aleksej.pagescroller.R;

import org.androidannotations.annotations.EActivity;

@EActivity(resName = "activity_main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
