package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Splash_Activity.class));

    }

    public void GoToStudy(View v){
        Intent it = new Intent(this,StudyMainActivity.class);
        startActivity(it);

    }

    public void GoToTest(View v){

    }

    public void GoToTranslate(View v){

    }

    public void GoToSupplement(View v){

    }

    public void ShowDeveloper(View v){

    }
}

