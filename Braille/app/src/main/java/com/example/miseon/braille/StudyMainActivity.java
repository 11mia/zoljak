package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class StudyMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_main);
        setTitle("학습");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    public void GoBack(View v){
        finish();
    }

    public void Consonant(View v){
        Intent it = new Intent(this,StudyConsonantActivity.class);
        startActivity(it);
    }

    public void vowel(View v){
        Intent it = new Intent(this,StudyVowelActivity.class);
        startActivity(it);
    }

    public void alphabet(View v){
        Intent it = new Intent(this,StudyAlphabetActivity.class);
        startActivity(it);
    }

    public void number(View v){
        Intent it = new Intent(this,StudyNumberActivity.class);
        startActivity(it);
    }

    public void symbol(View v){
        Intent it = new Intent(this,StudySymbolActivity.class);
        startActivity(it);
    }

    public void abbreviation(View v){
        Intent it = new Intent(this,StudyAbbreviationActivity.class);
        startActivity(it);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
