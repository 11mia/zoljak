package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class TestActivity extends AppCompatActivity{

    int count=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("테스트(글자->점자)");
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

    public void test(View v){
        //버튼 클릭시 테스트로 넘어가는거!

        Intent it = new Intent(TestActivity.this,TestContentActivity.class);
      //  int tag = it.getIntExtra("flag" ,1);
        startActivity(it);

    }
    public void GoBack(View v){
        finish();
    }


}
