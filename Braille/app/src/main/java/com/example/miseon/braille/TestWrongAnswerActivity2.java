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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TestWrongAnswerActivity2 extends AppCompatActivity {

    int count;

    String letter;
    String type;
    int dot_num;

    int answerNum;

    int[] randomNumList = new int[4];

    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    final Context context = this;


    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    LinearLayout dialog_layout;


    LinearLayout testLayout;

    int[] total_number = new int[7];//연습&테스트에서 총 시도횟수*문제수
    int[] incorrect_number = new int[7];//연습&테스트에서 틀린문제수->문제 하나당 딱 한번만 카운트.
    List<Integer> incorrect_list = new ArrayList<Integer>();//오답리스트->db의 num값을 저장.최대 50개.
    int incorrect_list_count;
    int Test_count;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent2);
        setTitle("테스트(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent it = getIntent();
        count=it.getIntExtra("count",1);
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list=it.getIntegerArrayListExtra("incorrect_list");
        incorrect_list_count=it.getIntExtra("incorrect_list_count",1);
        Test_count = (int)(incorrect_list_count/2)+1;
        Log.v("incorrect_list_count",Integer.toString(incorrect_list_count));
        Log.v("Practice_count",Integer.toString(Test_count));

        tv1 = (TextView)findViewById(R.id.testTv1);
        tv2 = (TextView)findViewById(R.id.testTv2);
        tv3 = (TextView)findViewById(R.id.testTv3);
        tv4 = (TextView)findViewById(R.id.testTv4);

        testLayout = (LinearLayout)findViewById(R.id.testLayout);


        int randomNum;
        Cursor cursor;

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();


            int id_img;
            Resources res = getResources();
            int temp;

            setTitle("오답 테스트(점자->글자)");
            for(int i=0;i<4;i++) {
                temp = randomRange(49-incorrect_list_count+1, 49);
                Log.v("randomRange : ",Integer.toString(49-incorrect_list_count+1)+"~"+Integer.toString(49));
                randomNum = incorrect_list.get(temp);
                randomNumList[i]=randomNum;
                for(int j=0;j<i;j++)
                    if(randomNumList[i]==randomNumList[j])
                        i--;
            }

            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                if(type.equals("단어초성")||type.equals("단어중성")||type.equals("단어종성")||type.equals("단어쌍자음초성")||type.equals("단어쌍자음종성")||type.equals("단어알파벳")||type.equals("한글약어")|type.equals("문장부호")||type.equals("대문자")||type.equals("소문자"))
                    tv1.setText(letter);
                else
                    tv1.setText(letter+"\n("+type+")");
            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                if(type.equals("단어초성")||type.equals("단어중성")||type.equals("단어종성")||type.equals("단어쌍자음초성")||type.equals("단어쌍자음종성")||type.equals("단어알파벳")||type.equals("한글약어")|type.equals("문장부호")||type.equals("대문자")||type.equals("소문자"))
                    tv2.setText(letter);
                else
                    tv2.setText(letter+"\n("+type+")");
            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                if(type.equals("단어초성")||type.equals("단어중성")||type.equals("단어종성")||type.equals("단어쌍자음초성")||type.equals("단어쌍자음종성")||type.equals("단어알파벳")||type.equals("한글약어")|type.equals("문장부호")||type.equals("대문자")||type.equals("소문자"))
                    tv3.setText(letter);
                else
                    tv3.setText(letter+"\n("+type+")");            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                if(type.equals("단어초성")||type.equals("단어중성")||type.equals("단어종성")||type.equals("단어쌍자음초성")||type.equals("단어쌍자음종성")||type.equals("단어알파벳")||type.equals("한글약어")|type.equals("문장부호")||type.equals("대문자")||type.equals("소문자"))
                    tv4.setText(letter);
                else
                    tv4.setText(letter+"\n("+type+")");            }

            answerNum = randomRange(0,3);
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[answerNum]},null,null,"num");
            if(cursor.moveToNext()){
                dot_num=cursor.getInt(cursor.getColumnIndex("dot_num"));
                for(int i=1;i<=dot_num;i++){

                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                    String str = cursor.getString(cursor.getColumnIndex("dot_"+i));
                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                    iv.setImageResource(id_img);
                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());//30dp
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());//50dp
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.
                    layoutParams.gravity = Gravity.CENTER;
                    iv.setLayoutParams(layoutParams);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    testLayout.setGravity(Gravity.CENTER);
                    testLayout.addView(iv);
                }

            }

            sqlitedb.setTransactionSuccessful();

        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();
        }

        sqlitedb.close();

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

    public void checkAnswer(View v) {
        int id = v.getId();
        Button clickedButn = (Button) findViewById(id);
        String input_tag = (String) clickedButn.getTag();
        int input = Integer.parseInt(input_tag);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_test_content3, null);
        dialog_layout = (LinearLayout)layout.findViewById(R.id.Testttt);

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();



            Cursor cursor = sqlitedb.query("Braille", null, "num=?", new String[]{"" + randomNumList[input]}, null, null, "num");
            if (cursor.moveToNext()) {
                letter = cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));

                Resources res = getResources();
                int id_img;

                for(int i=1;i<=dot_num;i++){

                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                    String str = cursor.getString(cursor.getColumnIndex("dot_"+i));
                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                    iv.setImageResource(id_img);
                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                    layoutParams.gravity = Gravity.CENTER;
                    iv.setLayoutParams(layoutParams);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    dialog_layout.setGravity(Gravity.CENTER);
                    dialog_layout.addView(iv);
                }
            }

            if (input == answerNum && count < Test_count) {
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Intent it = new Intent(TestWrongAnswerActivity2.this, TestWrongAnswerActivity2.class);
                                        it.putExtra("count", ++count);
                                        it.putExtra("total_number",total_number);
                                        it.putExtra("incorrect_number",incorrect_number);
                                        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                        it.putExtra("incorrect_list_count",incorrect_list_count);
                                        startActivity(it);
                                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                                        dialog_layout.removeAllViews();
                                        finish();
                                    }
                                });
                alertDialogBuilder.setTitle("정답입니다. 다음문제로 넘어갑니다.");

            } else if (input == answerNum && count == Test_count) {

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        dialog_layout.removeAllViews();
                                        finish();
                                    }
                                });
                alertDialogBuilder.setTitle("정답입니다. 연습을 종료합니다.");
            } else {
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        dialog_layout.removeAllViews();
                                    }
                                });
                alertDialogBuilder.setTitle("오답입니다. 다시 시도하세요.");

            }


            TextView text = (TextView) layout.findViewById(R.id.testText);
            if (type.equals("초성") | type.equals("중성") | type.equals("종성") | type.equals("된소리초성") | type.equals("된소리종성") | type.equals("소문자") | type.equals("대문자"))
                text.setText(letter + " (" + type + ")");
            else
                text.setText(letter);


            alertDialogBuilder.setView(layout);

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setIcon(R.drawable.braille);
            alertDialog.show();

            sqlitedb.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();
        }
    }


    public int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}


