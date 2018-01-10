package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StudyMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_main);
        setTitle("학습");


    }
    public void GoBack(View v){
        finish();
    }
    public void Consonant(View v){
        Intent it = new Intent(this,StudyConsonantActivity.class);
        startActivity(it);
    }
}
