package com.example.miseon.braille;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WrongAnswerActivity extends AppCompatActivity {
    int[] total_number = new int[7];//연습&테스트에서 총 시도횟수*문제수
    int[] incorrect_number = new int[7];//연습&테스트에서 틀린문제수->문제 하나당 딱 한번만 카운트.
    List<Integer> incorrect_list = new ArrayList<Integer>();//오답리스트->db의 num값을 저장.최대 50개.
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    LinearLayout layout_list;
    int incorrect_list_count=0;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        setTitle("오답 노트");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1 = (TextView)findViewById(R.id.calulate1);
        tv2 = (TextView)findViewById(R.id.calulate2);
        tv3 = (TextView)findViewById(R.id.calulate3);
        tv4 = (TextView)findViewById(R.id.calulate4);
        tv5 = (TextView)findViewById(R.id.calulate5);
        tv6 = (TextView)findViewById(R.id.calulate6);
        layout_list = (LinearLayout)findViewById(R.id.scroll);
        Intent it=getIntent();
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list = it.getIntegerArrayListExtra("incorrect_list");

        setTextView();

        //오답들 보이기
       int i=49;
        while(incorrect_list.get(i)!=0){
            incorrect_list_count++;
            try {
                dbmanager = new DBManager(this);
                sqlitedb = dbmanager.getReadableDatabase();
                Cursor cursor;
                sqlitedb.beginTransaction();
                LinearLayout layout = new LinearLayout(this);
                layout.setPadding(0,5,0,5);
                layout.setOrientation(LinearLayout.HORIZONTAL);

                final int width1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
                final int width2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 85, getResources().getDisplayMetrics());

                final int height1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width1, height1);//단위로 dp를 사용하기 위함.
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(width2, height1);//단위로 dp를 사용하기 위함.

                layoutParams1.gravity = Gravity.CENTER;

                TextView tv = new TextView(this);//letter
                tv.setLayoutParams(layoutParams1);
                tv.setTextSize(20);
                layoutParams1.setMargins(15,0,15,0);

                TextView tv0 = new TextView(this);//type
                tv0.setLayoutParams(layoutParams2);
                tv0.setTextSize(20);
                layoutParams2.setMargins(0,0,10,0);

                String letter;
                String str_type;
                int dot_num;
                int id_img;
                Resources res = getResources();

                int num=incorrect_list.get(i);
                cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+num},null,null,"num");
                if(cursor.moveToNext()){
                    letter =cursor.getString(cursor.getColumnIndex("letter"));
                    str_type = cursor.getString(cursor.getColumnIndex("type"));
                    dot_num=cursor.getInt(cursor.getColumnIndex("dot_num"));
                    tv.setText(letter);

                    if(str_type.equals("단어중성")||str_type.equals("단어초성")||str_type.equals("단어종성")||str_type.equals("단어쌍자음초성")||str_type.equals("단어쌍자음종성")||str_type.equals("단어알파벳"))
                        tv0.setText("(단어)");
                    else
                        tv0.setText("("+str_type+")");
                    layout.addView(tv);
                    layout.addView(tv0);
                   // layout.addView(empty);
                    for(int j=1;j<=dot_num;j++){

                        ImageView iv = new ImageView(this); //추가할 이미지뷰
                        String str = cursor.getString(cursor.getColumnIndex("dot_"+j));
                        id_img = res.getIdentifier(str, "drawable", getPackageName());
                        iv.setImageResource(id_img);
                        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                        layoutParams.gravity = Gravity.CENTER;
                        iv.setLayoutParams(layoutParams);
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        layout.addView(iv);
                    }
                    layout_list.addView(layout);
                    i--;


                }

                sqlitedb.setTransactionSuccessful();

            } catch (SQLiteException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }finally{
                sqlitedb.endTransaction();
            }

            sqlitedb.close();

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                Intent it = new Intent();
                it.putExtra("total_number",total_number);
                it.putExtra("incorrect_number",incorrect_number);
                it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                setResult(RESULT_OK, it);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//뒤로가기버튼누를시
    public void onBackPressed() {
        Intent it2 = new Intent();
        it2.putExtra("total_number",total_number);
        it2.putExtra("incorrect_number",incorrect_number);
        it2.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        setResult(RESULT_OK, it2);
        finish();
    }

    public void clickClear(View v){
        for(int i=0;i<7;i++){
            total_number[i]=0;
            incorrect_number[i]=0;
        }
        incorrect_list.clear();
        for(int i=0;i<50;i++) {
            incorrect_list.add(0);
            Log.v("incorrect_list " + i + " : ", Integer.toString(incorrect_list.get(i)));
        }
        incorrect_list_count=0;

        setTextView();

        layout_list.removeAllViews();

    }

    public void clickPractice(View v){
        if(incorrect_list_count==0) {
            Toast.makeText(this, "오답노트가 비어있습니다. \n연습을 실행 할 수 없습니다.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent it = new Intent(this,PracticeWrongAnswerActivity.class);
        it.putExtra("flag",9);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        it.putExtra("incorrect_list_count",incorrect_list_count);
        //startActivityForResult(it,2);
        startActivity(it);
    }

    public void clickTest(View v){
        if(incorrect_list_count==0) {
            Toast.makeText(this, "오답노트가 비어있습니다. \n테스트를 실행 할 수 없습니다.", Toast.LENGTH_LONG).show();
            return;
        }
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
                                Intent it = new Intent(WrongAnswerActivity.this,TestWrongAnswerActivity1.class);
                                it.putExtra("count",1);
                                it.putExtra("total_number",total_number);
                                it.putExtra("incorrect_number",incorrect_number);
                                it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                it.putExtra("incorrect_list_count",incorrect_list_count);
                                startActivity(it);
                            }
                        })
                .setNegativeButton("점자->글자",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent it = new Intent(WrongAnswerActivity.this,TestWrongAnswerActivity2.class);
                                it.putExtra("count",1);
                                it.putExtra("total_number",total_number);
                                it.putExtra("incorrect_number",incorrect_number);
                                it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                it.putExtra("incorrect_list_count",incorrect_list_count);

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



    public void setTextView(){
        String[] type = {"한글 자음","한글 모음","한글 약어","알파벳","숫자","문장부호","단어"};
        tv1.setText("타입");
        tv2.setText("오답");
        tv3.setText("전체");
        tv4.setText("타입");
        tv5.setText("오답");
        tv6.setText("전체");
        for(int i=0;i<7;i++){
            if(i%2==0) {
                tv1.append("\n" + type[i]);
                tv2.append("\n" + incorrect_number[i]);
                tv3.append("\n" + total_number[i]);
            }else{
                tv4.append("\n" + type[i]);
                tv5.append("\n" + incorrect_number[i]);
                tv6.append("\n" + total_number[i]);
            }
        }
        tv4.append("\n");
        tv5.append("\n");
        tv6.append("\n");
    }
}

