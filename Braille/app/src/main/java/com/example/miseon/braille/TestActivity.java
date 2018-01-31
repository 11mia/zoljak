package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class TestActivity extends AppCompatActivity{

    int count=0;
    int flag;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",1);

        if(flag==1){
            setTitle("테스트(글자->점자)");
        }
        else if(flag==2){
            setTitle("테스트(점자->글자)");
        }
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


    public void testConsonant(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",1);
        it.putExtra("count",1);
        startActivity(it);

    }

    public void testVowel(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",2);
        it.putExtra("count",1);
        startActivity(it);

    }

    public void testAbbreviation(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",3);
        it.putExtra("count",1);
        startActivity(it);
    }

    public void testAlphabet(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",4);
        it.putExtra("count",1);
        startActivity(it);
    }

    public void testNumber(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",5);
        it.putExtra("count",1);
        startActivity(it);
    }

    public void testSymbol(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",6);
        it.putExtra("count",1);
        startActivity(it);
    }

    public void testRandom(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",7);
        it.putExtra("count",1);
        startActivity(it);
    }

    public void GoBack(View v){
        finish();
    }


}
