package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TranslateMainActivity2 extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        setTitle("변환(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
                it=new Intent(this,TranslateActivity2.class);
                startActivity(it);
                finish();
                break;
            case R.id.translateEnglish:
                it=new Intent(this,TranslateActivity4.class);
                startActivity(it);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
