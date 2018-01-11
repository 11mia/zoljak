package com.example.miseon.braille;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestActivity2 extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("테스트(점자->글자)");
    }
    public void GoBack(View v){
        finish();
    }

    public void test(View v){
       int id;
        //버튼 클릭시 테스트로 넘어가는거!
        Resources res = getResources();
        id = v.getId();
        Button bt = (Button)v.findViewById(id);

        Intent it = new Intent(TestActivity2.this,TestContentActivity2.class);
       // int tag = it.getIntExtra("flag" ,1);
        startActivity(it);

    }

}
