package com.example.miseon.braille;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class TranslateActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate1);
        setTitle("변환(한글->점자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



/*
        List<String> jasoList = new ArrayList<String>();

        try {
            jasoList = HangulParser.disassemble('한');
        } catch (HangulParserException e) {
            e.printStackTrace();
        }

*/


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
