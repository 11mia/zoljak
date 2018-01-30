package com.example.miseon.braille;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PracticeContentActivity extends AppCompatActivity {
    int flag11=7;
    int flag12=7;
    int flag13=7;
    int flag14=7;
    int flag15=7;
    int flag16=7;
    int flag21=7;
    int flag22=7;
    int flag23=7;
    int flag24=7;
    int flag25=7;
    int flag26=7;
    int flag=0;
    Vibrator mVibe;
    int count;

    String letter;
    String type;
    int dot_num;
    String dot_1;
    String dot_2;

    String input1;
    String input2;

    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",1);
        count=it.getIntExtra("count",1);
        TextView tv = (TextView)findViewById(R.id.practiceLetter);

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();


            switch (flag) {
                case 1:
                    setTitle("점자연습-한글자음");
                    int randomNum = randomRange(1,35);
                    Cursor cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNum},null,null,"num");
                    if(cursor.moveToNext()){
                        letter = cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        dot_num=cursor.getInt(cursor.getColumnIndex("dot_num"));
                        if(dot_num==1){
                            dot_1=cursor.getString(cursor.getColumnIndex("dot_1"));
                            dot_2="c777777";
                        }else{
                            dot_1=cursor.getString(cursor.getColumnIndex("dot_1"));
                            dot_2=cursor.getString(cursor.getColumnIndex("dot_2"));
                        }
                        //Toast.makeText(this,"num="+randomNum+", letter="+letter+", type="+type+", dot_num="+dot_num+", dot_1="+dot_1+", dot_2="+dot_2,Toast.LENGTH_LONG).show();
                        //Toast.makeText(this,"count="+count,Toast.LENGTH_SHORT).show();

                        tv.setText(letter+"("+type+")");

                    }


                    break;
                case 2:
                    setTitle("점자연습-한글모음");


                    break;
                case 3:
                    setTitle("점자연습-한글약어");


                    break;
                case 4:
                    setTitle("점자연습-알파벳");


                    break;
                case 5:
                    setTitle("점자연습-숫자");


                    break;
                case 7:
                    setTitle("점자연습-랜덤");


                    break;

         /*   case 6:


                break;*/


            }
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

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

    public void ClickDot(View v){
        int id = v.getId();
        Button butn = (Button)findViewById(id);
        Resources res = getResources();
        Drawable drawable1 = res.getDrawable(R.drawable.shape_clicked_dot);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_dot);
        mVibe.vibrate(50);//0.05초


        String tag = (String)butn.getTag();

        switch(tag){
            case "11":
                if(flag11==7){//흰->검일경우
                    flag11=1;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag11=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "12":
                if(flag12==7){//흰->검일경우
                    flag12=2;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag12=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "13":
                if(flag13==7){//흰->검일경우
                    flag13=3;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag13=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "14":
                if(flag14==7){//흰->검일경우
                    flag14=4;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag14=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "15":
                if(flag15==7){//흰->검일경우
                    flag15=5;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag15=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "16":
                if(flag16==7){//흰->검일경우
                    flag16=6;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag16=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;

            case "21":
                if(flag21==7){//흰->검일경우
                    flag21=1;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag21=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "22":
                if(flag22==7){//흰->검일경우
                    flag22=2;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag22=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "23":
                if(flag23==7){//흰->검일경우
                    flag23=3;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag23=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "24":
                if(flag24==7){//흰->검일경우
                    flag24=4;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag24=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "25":
                if(flag25==7){//흰->검일경우
                    flag25=5;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag25=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "26":
                if(flag26==7){//흰->검일경우
                    flag26=6;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag26=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
        }
    }

    public void checkAnswer(View v){
//정답&&count==10일 경우 토스트로 연습이 종료됨을 알리고 PracticeMainActivity로 돌아간다.
        int num1;int num2;
        num1 = flag11*100000+flag12*10000+flag13*1000+flag14*100+flag15*10+flag16;
        num2 = flag21*100000+flag22*10000+flag23*1000+flag24*100+flag25*10+flag26;
        input1= "c"+num1;
        input2= "c"+num2;

        if(input1.equals(dot_1)&&input2.equals(dot_2)&&(count!=3)){
            Toast.makeText(this,"정답입니다.",Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this,PracticeContentActivity.class);
            it.putExtra("flag",1);
            it.putExtra("count",++count);
            startActivity(it);
            finish();
        }else if(input1.equals(dot_1)&&input2.equals(dot_2)&&(count==3)){
            Toast.makeText(this,"정답입니다. 연습을 종료합니다.",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"오답입니다. 다시 시도하세요.",Toast.LENGTH_SHORT).show();
            initialization();


        }

    }

    public void hint(View v){

    }

    public int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }

    public void initialization(){

        Resources res = getResources();

        flag11 = flag12 = flag13 = flag14 = flag15 = flag16 = flag21 = flag22 = flag23 = flag24 = flag25 = flag26 = 7;
        Drawable drawable = res.getDrawable(R.drawable.shape_dot);
        Button butn = (Button) findViewById(R.id.dot11);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot12);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot13);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot14);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot15);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot16);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot21);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot22);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot23);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot24);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot25);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot26);
        butn.setBackgroundDrawable(drawable);

    }


}
