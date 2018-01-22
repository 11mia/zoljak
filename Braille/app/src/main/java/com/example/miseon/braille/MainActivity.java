package com.example.miseon.braille;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Splash_Activity.class));

    }

    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
                //pressedTime = 0 ;
                pressedTime = System.currentTimeMillis();

            }
            else {
                super.onBackPressed();
                finish();
            }
        }
    }




    public void GoToStudy(View v){
        Intent it = new Intent(this,StudyMainActivity.class);
        startActivity(it);

    }

    public void GoToTest(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // 제목셋팅
        alertDialogBuilder.setTitle("테스트 선택");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("진행할 테스트를 선택하세요")
                .setCancelable(false)
                .setPositiveButton("글자->점자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TestActivity.class);
                                it.putExtra("flag",1);
                                startActivity(it);
                            }
                        })
                .setNegativeButton("점자->글자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TestActivity2.class);
                                it.putExtra("flag",2);
                                startActivity(it);
                            }
                        })
                .setNeutralButton("닫기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setIcon(R.drawable.braille);

        // 다이얼로그 보여주기
        alertDialog.show();


    }

    public void GoToTranslate(View v){

        /*
        fragment안에서는 기능에 제약사항이 많아지기에 activity를 나눠봄
        */
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // 제목셋팅
        alertDialogBuilder.setTitle("변환 선택");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("진행할 변환을 선택하세요")
                .setCancelable(false)
                .setPositiveButton("한글->점자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TranslateActivity1.class);
                                it.putExtra("flag",1);
                                startActivity(it);
                            }
                        })
                .setNegativeButton("점자->한글",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TranslateActivity2.class);
                                it.putExtra("flag",2);
                                startActivity(it);
                            }
                        })
                .setNeutralButton("닫기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setIcon(R.drawable.braille);

        // 다이얼로그 보여주기
        alertDialog.show();


    }

    public void GoToSupplement(View v){

    }

    public void ShowDeveloper(View v){
        Intent it = new Intent(this,DeveloperActivity.class);
        startActivity(it);

    }
}

