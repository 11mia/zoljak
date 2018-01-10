package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("테스트선택");


    }
    public void GoBack(View v){
        finish();
    }

    public void test(View v){   //버튼 클릭시 테스트로 넘어가는거!
        Intent it = new Intent(this,TestContentActivity.class);
        startActivity(it);

    }

}
