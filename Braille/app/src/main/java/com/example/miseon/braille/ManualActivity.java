package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


public class ManualActivity extends AppCompatActivity {
    int page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        setTitle("도움말");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        page =it.getIntExtra("page",1);

        switch (page){
            case 1:


        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickPrevious(View v){

    }

    public void clickNext(View v){

    }
}
