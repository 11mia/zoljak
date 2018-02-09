package com.example.miseon.braille;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

public class TranslateActivity3 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate1);
        setTitle("변환(영어->점자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //키보드 올리기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_translate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
            case R.id.translateHangul:
                it=new Intent(this,TranslateActivity1.class);
                startActivity(it);
                finish();
                break;
            case R.id.translateEnglish:
               // it=new Intent(this,TranslateActivity3.class);
               // startActivity(it);
               // finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
