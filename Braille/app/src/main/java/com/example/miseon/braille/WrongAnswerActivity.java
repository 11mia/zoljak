package com.example.miseon.braille;


import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        setTitle("오답 노트");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv1 = (TextView)findViewById(R.id.calulate1);
        TextView tv2 = (TextView)findViewById(R.id.calulate2);
        TextView tv3 = (TextView)findViewById(R.id.calulate3);
        TextView tv4 = (TextView)findViewById(R.id.calulate4);
        TextView tv5 = (TextView)findViewById(R.id.calulate5);
        TextView tv6 = (TextView)findViewById(R.id.calulate6);
        LinearLayout layout_list = (LinearLayout)findViewById(R.id.scroll);
        Intent it=getIntent();
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list = it.getIntegerArrayListExtra("incorrect_list");

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

        //오답들 보이기
       int i=49;
        while(incorrect_list.get(i)!=0){
            try {
                dbmanager = new DBManager(this);
                sqlitedb = dbmanager.getReadableDatabase();
                Cursor cursor;
                sqlitedb.beginTransaction();
                LinearLayout layout = new LinearLayout(this);
                layout.setPadding(0,5,0,5);
                layout.setOrientation(LinearLayout.HORIZONTAL);

                final int width1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
                final int width2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 85, getResources().getDisplayMetrics());

                final int height1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width1, height1);//단위로 dp를 사용하기 위함.
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(width2, height1);//단위로 dp를 사용하기 위함.

                layoutParams1.gravity = Gravity.CENTER;

                TextView tv = new TextView(this);//letter

                tv.setLayoutParams(layoutParams1);
                tv.setTextSize(20);
                layoutParams1.setMargins(10,0,0,0);

                TextView tv0 = new TextView(this);//type

                tv0.setLayoutParams(layoutParams2);
                tv0.setTextSize(20);

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
                setResult(1, it);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

