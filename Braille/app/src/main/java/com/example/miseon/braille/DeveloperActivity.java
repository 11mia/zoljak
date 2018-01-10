package com.example.miseon.braille;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DeveloperActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setTitle("개발자");


    }
    public void GoBack(View v){
        finish();
    }

}
