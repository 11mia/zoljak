package com.example.miseon.braille;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PracticeWrongAnswerActivity extends AppCompatActivity {
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

    final Context context = this;

    int[] total_number = new int[7];//연습&테스트에서 총 시도횟수*문제수
    int[] incorrect_number = new int[7];//연습&테스트에서 틀린문제수->문제 하나당 딱 한번만 카운트.
    List<Integer> incorrect_list = new ArrayList<Integer>();//오답리스트->db의 num값을 저장.최대 50개.
    int randomNum;
    int incorrect_list_count;
    int Practice_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",1);
        count=it.getIntExtra("count",1);
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list=it.getIntegerArrayListExtra("incorrect_list");
        incorrect_list_count=it.getIntExtra("incorrect_list_count",1);
        Practice_count = (int)(incorrect_list_count/2)+1;
        Log.v("incorrect_list_count",Integer.toString(incorrect_list_count));
        Log.v("Practice_count",Integer.toString(Practice_count));

        TextView tv = (TextView)findViewById(R.id.practiceLetter);
        TextView tv1=(TextView)findViewById(R.id.practiceType);
        Cursor cursor;


        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            setTitle("점자연습-오답");
            int temp;
            boolean run=true;
            do {
                temp = randomRange(0, 49);
                randomNum = incorrect_list.get(temp);
                cursor = sqlitedb.query("Braille", null, "num=?", new String[]{"" + randomNum}, null, null, "num");
                if (cursor.moveToNext()) {
                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                    type = cursor.getString(cursor.getColumnIndex("type"));
                    dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
                    if(dot_num>=4)
                        continue;
                    else
                        run=false;
                    if (dot_num == 1) {
                        dot_1 = cursor.getString(cursor.getColumnIndex("dot_1"));
                        dot_2 = "c777777";
                    } else {
                        dot_1 = cursor.getString(cursor.getColumnIndex("dot_1"));
                        dot_2 = cursor.getString(cursor.getColumnIndex("dot_2"));
                    }
                    tv.setText(letter);
                    if(type.equals("초성")|type.equals("중성")|type.equals("종성")|type.equals("된소리초성")|type.equals("된소리종성"))
                        tv1.setText("("+type+")");
                }
            }while(run);


        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Log.v("randomNum",Integer.toString(randomNum));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//액션바의 뒤로가기버튼선택시
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//뒤로가기버튼누를시
    public void onBackPressed() {
        finish();
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("정답 확인");

        if(input1.equals(dot_1)&&input2.equals(dot_2)&&(count!=Practice_count)){
            // Toast.makeText(this,"정답입니다.",Toast.LENGTH_SHORT).show();

            // AlertDialog 셋팅
            alertDialogBuilder
                    .setMessage("    정답입니다.\n\n    다음문제로 넘어갑니다.")
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    Intent it = new Intent(PracticeWrongAnswerActivity.this,PracticeWrongAnswerActivity.class);
                                    it.putExtra("flag",flag);
                                    it.putExtra("count",++count);
                                    it.putExtra("total_number",total_number);
                                    it.putExtra("incorrect_number",incorrect_number);
                                    it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                    it.putExtra("incorrect_list_count",incorrect_list_count);
/*
                                    it.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
*/
                                    startActivity(it);
                                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                                    finish();
                                }
                            });

            // 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setIcon(R.drawable.braille);

            // 다이얼로그 보여주기
            alertDialog.show();


        }else if(input1.equals(dot_1)&&input2.equals(dot_2)&&(count==Practice_count)){
            // Toast.makeText(this,"정답입니다. 연습을 종료합니다.",Toast.LENGTH_SHORT).show();

            alertDialogBuilder
                    .setMessage("    정답입니다.\n\n    연습을 종료합니다.")
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

            // 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setIcon(R.drawable.braille);

            // 다이얼로그 보여주기
            alertDialog.show();

        }else{
            alertDialogBuilder
                    .setMessage("    오답입니다.\n\n    다시 시도하세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    initialization();

                                }
                            });

            // 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setIcon(R.drawable.braille);

            // 다이얼로그 보여주기
            alertDialog.show();


        }

    }

    public void hint(View v){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        alertDialogBuilder.setTitle("힌트")
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                            }
                        });

        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_practice_hint, null);

        TextView text = (TextView) layout.findViewById(R.id.hintText);
        text.setText(letter+" ("+type+")");

        Resources res = getResources();
        int id_img;


        ImageView image = (ImageView) layout.findViewById(R.id.hintImage1);

        id_img = res.getIdentifier(dot_1, "drawable", getPackageName());
        image.setImageResource(id_img);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        image = (ImageView) layout.findViewById(R.id.hintImage2);

        id_img = res.getIdentifier(dot_2, "drawable", getPackageName());
        image.setImageResource(id_img);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        alertDialogBuilder.setView(layout);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setIcon(R.drawable.braille);

        alertDialog.show();



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

