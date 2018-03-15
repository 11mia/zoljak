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


public class TestContentActivity2 extends AppCompatActivity {

    int flag=0;
    int count;
    int semiFlag;

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
    boolean incorrect = false;
    int incorrect_num;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent2);
       // setTitle("테스트(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent it = getIntent();
        flag = it.getIntExtra("case",1);
        count=it.getIntExtra("count",1);
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list = it.getIntegerArrayListExtra("incorrect_list");
        incorrect_num=it.getIntExtra("incorrect_num",0);


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

            switch (flag) {
                case 0:
                    setTitle("테스트-한글자음("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(1,35);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 1:
                    setTitle("테스트-한글모음("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(36,56);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 2:
                    setTitle("테스트-한글약어("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(57,89);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 3:
                    setTitle("테스트-알파벳("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(90,141);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 4:
                    setTitle("테스트-숫자("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(142,151);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 7:
                    setTitle("테스트-랜덤("+count+"/7)");

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(1,320);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j]||randomNumList[i]==152)
                                i--;
                    }
                    break;

                case 5:
                    setTitle("테스트-문장부호("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(153,180);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;

                case 6:
                    setTitle("테스트-단어("+count+"/7)");
                    total_number[flag] = total_number[flag]+1;

                    for(int i=0;i<4;i++) {
                        randomNum=randomRange(183,320);
                        randomNumList[i]=randomNum;
                        for(int j=0;j<i;j++)
                            if(randomNumList[i]==randomNumList[j])
                                i--;
                    }
                    break;
            }


            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[0]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                switch(type){
                    case "초성":
                    case "종성":
                    case "중성":
                    case "된소리초성":
                    case "된소리종성"://한글자음
                        tv1.setText(letter+"\n("+type+")");
                        break;

                    case "소문자":
                    case "단어알파벳":
                        tv1.setAllCaps(false);
                        tv1.setText(letter);
                        break;

                    default:
                        if(letter.equals("0"))
                            tv1.setText(letter+"\n("+type+")");
                        else
                            tv1.setText(letter);
                        break;
                }
            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[1]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                switch(type){
                    case "초성":
                    case "종성":
                    case "중성":
                    case "된소리초성":
                    case "된소리종성"://한글자음
                        tv2.setText(letter+"\n("+type+")");
                        break;

                    case "소문자":
                    case "단어알파벳":
                        tv2.setAllCaps(false);
                        tv2.setText(letter);
                        break;

                    default:
                        tv2.setText(letter);
                        break;
                }
            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[2]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                switch(type){
                    case "초성":
                    case "종성":
                    case "중성":
                    case "된소리초성":
                    case "된소리종성"://한글자음
                        tv3.setText(letter+"\n("+type+")");
                        break;

                    case "소문자":
                    case "단어알파벳":
                        tv3.setAllCaps(false);
                        tv3.setText(letter);
                        break;

                    default:
                        tv3.setText(letter);
                        break;
                }            }
            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+randomNumList[3]},null,null,"num");
            if(cursor.moveToNext()){
                letter=cursor.getString(cursor.getColumnIndex("letter"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                switch(type){
                    case "초성":
                    case "종성":
                    case "중성":
                    case "된소리초성":
                    case "된소리종성"://한글자음
                        tv4.setText(letter+"\n("+type+")");
                        break;

                    case "소문자":
                    case "단어알파벳":
                        tv4.setAllCaps(false);
                        tv4.setText(letter);
                        break;

                    default:
                        tv4.setText(letter);
                        break;
                }
            }


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

            if(flag==7){

                if(randomNumList[answerNum]<=35)//자음
                    semiFlag=0;
                else if(randomNumList[answerNum]<=56)//모음
                    semiFlag=1;
                else if(randomNumList[answerNum]<=89)//약어
                    semiFlag=2;
                else if(randomNumList[answerNum]<=141)//알파벳
                    semiFlag=3;
                else if(randomNumList[answerNum]<=151)//숫자
                    semiFlag=4;
                else if(randomNumList[answerNum]<=182)//문장기호
                    semiFlag=5;
                else
                    semiFlag=6;//단어

                total_number[semiFlag] = total_number[semiFlag]+1;

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
                Intent it1 = new Intent();
                it1.putExtra("total_number",total_number);
                it1.putExtra("incorrect_number",incorrect_number);
                it1.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                setResult(RESULT_OK, it1);
                Log.v("TestContent2setResult","ok");
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//뒤로가기버튼누를시
    public void onBackPressed() {
        Intent it2 = new Intent();
        it2.putExtra("total_number", total_number);
        it2.putExtra("incorrect_number", incorrect_number);
        it2.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        setResult(RESULT_OK, it2);
        Log.v("TestContentsetResult", "ok");

        finish();
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



            if (input == answerNum && count < 7) {
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Intent it = new Intent(TestContentActivity2.this, TestContentActivity2.class);
                                        it.putExtra("case", flag);
                                        it.putExtra("count", ++count);
                                        it.putExtra("total_number",total_number);
                                        it.putExtra("incorrect_number",incorrect_number);
                                        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                        it.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                                        it.putExtra("incorrect_num",incorrect_num);

                                        startActivity(it);
                                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                                        dialog_layout.removeAllViews();
                                        finish();
                                    }
                                });
                alertDialogBuilder.setTitle("정답입니다.\n다음문제로 넘어갑니다.");

            } else if (input == answerNum && count == 7) {

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Intent it3=new Intent();
                                        it3.putExtra("total_number",total_number);
                                        it3.putExtra("incorrect_number",incorrect_number);
                                        it3.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
                                        setResult(RESULT_OK, it3);
                                        dialog_layout.removeAllViews();
                                        finish();
                                    }
                                })
                        .setMessage("\n   총 문제 : 7개\n   오답 : "+incorrect_num+"개\n    정답 : "+(7-incorrect_num)+"개");
                alertDialogBuilder.setTitle("정답입니다.\n연습을 종료합니다.");
            } else {
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        if(!incorrect){
                                            incorrect=true;
                                            incorrect_num++;
                                            if(flag!=7)
                                                incorrect_number[flag] = incorrect_number[flag] + 1;
                                            else
                                                incorrect_number[semiFlag] = incorrect_number[semiFlag] + 1;
                                            incorrect_list.add(randomNumList[answerNum]);
                                            if(incorrect_list.size()>50)
                                                incorrect_list.remove(0);
                                            for(int i=0;i<incorrect_list.size();i++)
                                                Log.v("incorrect_list"+i+" : ",Integer.toString(incorrect_list.get(i)));
                                        }
                                        dialog_layout.removeAllViews();
                                    }
                                });
                alertDialogBuilder.setTitle("오답입니다.\n다시 시도하세요.");

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
