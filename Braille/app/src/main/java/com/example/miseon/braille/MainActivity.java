package com.example.miseon.braille;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    long pressedTime = 0;
    int[] total_number = new int[7];//연습&테스트에서 총 시도횟수*문제수
    int[] incorrect_number = new int[7];//연습&테스트에서 틀린문제수->문제 하나당 딱 한번만 카운트.
    List<Integer> incorrect_list = new ArrayList<Integer>();//오답리스트->db의 num값을 저장.최대 50개.
   // int incorrect_list_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startActivity(new Intent(this, Splash_Activity.class));

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            Cursor cursor;
            int num;
            sqlitedb.beginTransaction();
            cursor = sqlitedb.query("Total", null, null, null, null, null, null);
            int i = 0;
            while (cursor.moveToNext()) {
                num = cursor.getInt(cursor.getColumnIndex("num"));
                total_number[i] = num;
                Log.v("total_number"+i+" : ",Integer.toString(total_number[i]));
                i++;
            }

            cursor = sqlitedb.query("IncorrectNum",null,null,null,null,null,null);
            i=0;
            while(cursor.moveToNext()){
                num =cursor.getInt(cursor.getColumnIndex("num"));
                incorrect_number[i]=num;
                Log.v("incorrect_number"+i+" : ",Integer.toString(incorrect_number[i]));
                i++;
            }

            cursor = sqlitedb.query("IncorrectList",null,null,null,null,null,null);
            i=0;
            while(cursor.moveToNext()) {
                num =cursor.getInt(cursor.getColumnIndex("num"));
                incorrect_list.add(num);
                Log.v("incorrect_list"+i+" : ",Integer.toString(incorrect_list.get(i)));
                i++;
            }


            sqlitedb.setTransactionSuccessful();

        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();
        }

        sqlitedb.close();





        sqlitedb.close();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //여기서는 데이터를 db에 저장까지!
        super.onActivityResult(requestCode, resultCode, data);
        total_number = data.getIntArrayExtra("total_number");
        incorrect_number=data.getIntArrayExtra("incorrect_number");
        incorrect_list=data.getIntegerArrayListExtra("incorrect_list");
        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            Cursor cursor;
            sqlitedb.beginTransaction();
            ContentValues values1 = new ContentValues();
            ContentValues values2 = new ContentValues();
            ContentValues values3 = new ContentValues();
            String[] type = {"한글자음","한글모음","한글약어","알파벳","숫자","문장부호","단어"};

            sqlitedb.delete("Total",null,null);
            Log.v("total 삭제","ok");
            sqlitedb.delete("IncorrectNum",null,null);
            Log.v("IncorrectNum 삭제","ok");
            sqlitedb.delete("IncorrectList",null,null);
            Log.v("IncorrectList 삭제","ok");


            for(int i=0;i<7;i++){
                values1.put("type",type[i]);
                values1.put("num",total_number[i]);
                sqlitedb.insert("Total",null,values1);
                Log.v("total 저장","ok");
            }
            for(int i=0;i<7;i++){
                values2.put("type",type[i]);
                values2.put("num",incorrect_number[i]);
                sqlitedb.insert("IncorrectNum",null,values2);
                Log.v("incorrect_number 저장","ok");

            }
            for(int i=0;i<50;i++){
                values3.put("rec_num",i);
                values3.put("num",incorrect_list.get(i));
                sqlitedb.insert("IncorrectList",null,values3);
                Log.v("IncorrectList 저장","ok");

            }


            sqlitedb.setTransactionSuccessful();

        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();
        }

        sqlitedb.close();




            for(int i=0;i<6;i++) {
            Log.v("total_number"+i+" : ", Integer.toString(total_number[i]));
            Log.v("incorrect_number"+i+" : ", Integer.toString(incorrect_number[i]));
        }
        for(int i=0;i<incorrect_list.size();i++)
            Log.v("incorrect_list"+i+" : ",Integer.toString(incorrect_list.get(i)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.action_settings){
            Intent it = new Intent(this,DeveloperActivity.class);
            startActivity(it);
            return true;
        }
        if(id==R.id.action_settings2){
           finish();
        }
        if(id==R.id.action_settings3){
            //도움말 추가하기!
            Intent it = new Intent(this,ManualActivity.class);
            it.putExtra("page",1);
            startActivity(it);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void studyBraille(View v){
        Intent it = new Intent(this,StudyMainActivity.class);
        startActivity(it);

    }


    public void aboutBraille(View v){
        Intent it = new Intent(this,StoryBookActivity.class);
        startActivity(it);

    }

    public void practiceBraille(View v){
        Intent it = new Intent(this,PracticeMainActivity.class);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);


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
                                it.putExtra("total_number",total_number);
                                it.putExtra("incorrect_number",incorrect_number);
                                it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                //startActivity(it);
                                startActivityForResult(it,3);
                            }
                        })
                .setNegativeButton("점자->글자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TestActivity.class);
                                it.putExtra("flag",2);
                                it.putExtra("total_number",total_number);
                                it.putExtra("incorrect_number",incorrect_number);
                                it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                //startActivity(it);
                                startActivityForResult(it,4);
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // 제목셋팅
        alertDialogBuilder.setTitle("변환 선택");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("진행할 변환을 선택하세요")
                .setCancelable(false)
                .setPositiveButton("글자->점자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TranslateMainActivity.class);
                                it.putExtra("flag",1);
                                startActivity(it);
                            }
                        })
                .setNegativeButton("점자->글자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(MainActivity.this,TranslateMainActivity2.class);
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
        Intent it = new Intent(this,SupplementActivity.class);
        startActivity(it);

    }



}

