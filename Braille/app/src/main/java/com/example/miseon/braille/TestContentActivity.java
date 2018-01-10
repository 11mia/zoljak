package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class TestContentActivity extends AppCompatActivity {
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent);
        setTitle("테스트");

        Intent it = getIntent();
        flag = it.getIntExtra("flag",1);    //alertdialog에서 넘긴 flag값 꺼내기.flag값이 잘못넘어왔을 경우 디폴트를 1로 set


    }
}
