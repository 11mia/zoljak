package com.example.miseon.braille;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;



public class TranslateActivity2 extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate2);
        setTitle("변환(점자->한글)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
