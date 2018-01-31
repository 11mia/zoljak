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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TestContentActivity2 extends AppCompatActivity {

    int flag=0;
    int count;

    String letter;
    String type;
    int dot_num;
    String dot_1;
    String dot_2;

    int answerNum;

    int[] randomNumList = new int[4];

    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    final Context context = this;

    ImageView testImg0; //dot_num==1일 경우
    ImageView testImg1; //dot_num==2일 경우
    ImageView testImg2; //dot_num==2일 경우

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    String lowercase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent2);
        setTitle("테스트(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent it = getIntent();
        flag = it.getIntExtra("case",1);
        count=it.getIntExtra("count",1);

        testImg0 =(ImageView)findViewById(R.id.testImg0);
        testImg1 =(ImageView)findViewById(R.id.testImg1);
        testImg2 =(ImageView)findViewById(R.id.testImg2);

        tv1 = (TextView)findViewById(R.id.testTv1);
        tv2 = (TextView)findViewById(R.id.testTv2);
        tv3 = (TextView)findViewById(R.id.testTv3);
        tv4 = (TextView)findViewById(R.id.testTv4);


        int randomNum;
        Cursor cursor;

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();


            int id_img;
            Resources res = getResources();

            switch (flag) {
                case 1:
                    setTitle("테스트-한글자음");
                    for(int i=0;i<4;i++) {

                        randomNum=randomRange(1,35);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }

                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        tv1.setText(letter+"\n("+type+")");
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        tv2.setText(letter+"\n("+type+")");
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        tv3.setText(letter+"\n("+type+")");
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        tv4.setText(letter+"\n("+type+")");
                    }
                    break;
                case 2:
                    setTitle("테스트-한글모음");

                    for(int i=0;i<4;i++) {

                        randomNum=randomRange(36,56);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }

                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv1.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv2.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv3.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv4.setText(letter);
                    }

                    break;
                case 3:
                    setTitle("테스트-한글약어");

                    for(int i=0;i<4;i++) {

                        randomNum=randomRange(57,89);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }

                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv1.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv2.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv3.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv4.setText(letter);
                    }

                    break;
                case 4:
                    setTitle("테스트-알파벳");

                    for(int i=0;i<4;i++) {

                        randomNum=randomRange(90,141);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }

                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                       // if(type.equals("알파벳소문자"))
                     //       lowercase = letter.toLowerCase();
                       // tv1.setText(lowercase);
                        tv1.setText(letter+"\n("+type+")");

                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                       /* if(type.equals("알파벳소문자"))
                            lowercase = letter.toLowerCase();
                        tv2.setText(lowercase);*/
                        tv2.setText(letter+"\n("+type+")");

                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                      /*  if(type.equals("알파벳소문자"))
                            lowercase = letter.toLowerCase();
                        tv3.setText(lowercase);*/
                        tv3.setText(letter+"\n("+type+")");

                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                      /*  if(type.equals("알파벳소문자"))
                            lowercase = letter.toLowerCase();
                        tv4.setText(lowercase);*/
                        tv4.setText(letter+"\n("+type+")");

                    }

                    break;
                case 5:
                    setTitle("테스트-숫자");

                    for(int i=0;i<4;i++) {

                        randomNum=randomRange(142,151);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }


                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv1.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv2.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv3.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv4.setText(letter);
                    }

                    break;
                case 7:
                    setTitle("테스트-랜덤");

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(1,180);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j]||randomNumList[i]==152)
                                i--;

                    }

                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));

                        if(type.equals("초성")|type.equals("중성")|type.equals("종성")|type.equals("된소리초성")|type.equals("된소리종성")|type.equals("소문자")|type.equals("대문자"))
                            tv1.setText(letter+"\n("+type+")");
                        /*else if(type.equals("알파벳소문자")){
                            letter = letter.toLowerCase();
                            tv1.setText(letter);
                        }*/
                        else
                            tv1.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));

                        if(type.equals("초성")|type.equals("중성")|type.equals("종성")|type.equals("된소리초성")|type.equals("된소리종성")|type.equals("소문자")|type.equals("대문자"))
                            tv2.setText(letter+"\n("+type+")");
                        /**else if(type.equals("알파벳소문자")){
                            letter = letter.toLowerCase();
                            tv2.setText(letter);
                        }*/
                        else
                            tv2.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));

                        if(type.equals("초성")|type.equals("중성")|type.equals("종성")|type.equals("된소리초성")|type.equals("된소리종성")|type.equals("소문자")|type.equals("대문자"))
                            tv3.setText(letter+"\n("+type+")");
                       /* else if(type.equals("알파벳소문자")){
                           letter = letter.toLowerCase();
                            tv3.setText(letter);
                        }*/
                        else
                            tv3.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        type = cursor.getString(cursor.getColumnIndex("type"));

                        if(type.equals("초성")|type.equals("중성")|type.equals("종성")|type.equals("된소리초성")|type.equals("된소리종성")|type.equals("소문자")|type.equals("대문자"))
                            tv4.setText(letter+"\n("+type+")");
                        /*else if(type.equals("알파벳소문자")){
                            letter = letter.toLowerCase();
                            tv4.setText(letter);
                        }*/
                        else
                            tv4.setText(letter);
                    }

                    break;

                case 6:
                    setTitle("테스트-문장부호");

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(153,180);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;

                    }


                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv1.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv2.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv3.setText(letter);
                    }
                    cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
                    if(cursor.moveToNext()){
                        letter=cursor.getString(cursor.getColumnIndex("letter"));
                        tv4.setText(letter);
                    }

                    break;


            }

            answerNum = randomRange(0,3);
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[answerNum]},null,null,"num");
            if(cursor.moveToNext()){
                dot_num=cursor.getInt(cursor.getColumnIndex("dot_num"));
                if(dot_num==1){
                    dot_1=cursor.getString(cursor.getColumnIndex("dot_1"));
                    id_img = res.getIdentifier(dot_1, "drawable", getPackageName());
                    testImg0.setImageResource(id_img);
                    testImg0.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    dot_1=cursor.getString(cursor.getColumnIndex("dot_1"));
                    dot_2=cursor.getString(cursor.getColumnIndex("dot_2"));

                    id_img = res.getIdentifier(dot_1, "drawable", getPackageName());
                    testImg1.setImageResource(id_img);
                    testImg1.setScaleType(ImageView.ScaleType.FIT_XY);
                    id_img = res.getIdentifier(dot_2, "drawable", getPackageName());
                    testImg2.setImageResource(id_img);
                    testImg2.setScaleType(ImageView.ScaleType.FIT_XY);
                }

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

    public void checkAnswer(View v){
        int id = v.getId();
        Button clickedButn = (Button)findViewById(id);
        String input_tag = (String)clickedButn.getTag();
        int input = Integer.parseInt(input_tag);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        Cursor cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[input]},null,null,"num");
        if(cursor.moveToNext()) {
            letter = cursor.getString(cursor.getColumnIndex("letter"));
            type = cursor.getString(cursor.getColumnIndex("type"));
            dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
            if (dot_num == 1) {
                dot_1 = cursor.getString(cursor.getColumnIndex("dot_1"));
            } else {
                dot_1 = cursor.getString(cursor.getColumnIndex("dot_1"));
                dot_2 = cursor.getString(cursor.getColumnIndex("dot_2"));
            }
        }
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_test_content2, null);





        if(input==answerNum&&count<3) {
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    Intent it = new Intent(TestContentActivity2.this, TestContentActivity2.class);
                                    it.putExtra("case", flag);
                                    it.putExtra("count", ++count);
                                    startActivity(it);
                                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                                    finish();
                                }
                            });
            alertDialogBuilder.setTitle("정답입니다. 다음문제로 넘어갑니다.");

        }
        else if(input==answerNum&&count==3){

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    finish();
                                }
                            });
            alertDialogBuilder.setTitle("정답입니다. 연습을 종료합니다.");
        }
        else{
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                }
                            });
            alertDialogBuilder.setTitle("오답입니다. 다시 시도하세요.");

        }


            TextView text = (TextView) layout.findViewById(R.id.testText);
            text.setText(letter+" ("+type+")");

            Resources res = getResources();
            int id_img;

            if(dot_num==1) {
                ImageView image = (ImageView) layout.findViewById(R.id.testImage0);
                id_img = res.getIdentifier(dot_1, "drawable", getPackageName());
                image.setImageResource(id_img);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
            }


            else if(dot_num==2) {
                ImageView image = (ImageView) layout.findViewById(R.id.testImage1);
                id_img = res.getIdentifier(dot_1, "drawable", getPackageName());
                image.setImageResource(id_img);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image = (ImageView) layout.findViewById(R.id.testImage2);
                id_img = res.getIdentifier(dot_2, "drawable", getPackageName());
                image.setImageResource(id_img);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
            }



            alertDialogBuilder.setView(layout);

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setIcon(R.drawable.braille);
            alertDialog.show();
        }



    public int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}
