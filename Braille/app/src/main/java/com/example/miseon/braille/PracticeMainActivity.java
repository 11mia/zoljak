package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by miseon on 2018-01-30.
 */

public class PracticeMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_main);
        setTitle("점자연습");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public void practiceConsonant(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",1);
        startActivity(it);

    }


    public void practiceVowel(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",2);
        startActivity(it);
    }

    public void practiceAbbreviation(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",3);
        startActivity(it);
    }

    public void practiceAlphabet(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",4);
        startActivity(it);
    }

    public void practiceNumber(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",5);
        startActivity(it);
    }

    public void practiceSymbol(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",6);
        startActivity(it);
    }

    public void practiceRandom(View v){
        Intent it = new Intent(this,PracticeContentActivity.class);
        it.putExtra("flag",7);
        startActivity(it);
    }

}
