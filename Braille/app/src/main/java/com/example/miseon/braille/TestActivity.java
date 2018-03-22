package com.example.miseon.braille;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity{

    int[] total_number = new int[7];//연습&테스트에서 총 시도횟수*문제수
    int[] incorrect_number = new int[7];//연습&테스트에서 틀린문제수->문제 하나당 딱 한번만 카운트.
    List<Integer> incorrect_list = new ArrayList<Integer>();//오답리스트->db의 num값을 저장.최대 50개.
    double[] percent = new double[7];
    TextView tv;


    //int count=0;
    int flag;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        Intent it = getIntent();
        flag = it.getIntExtra("flag",1);

        if(flag==1){
            setTitle("테스트(글자->점자)");
        }
        else if(flag==2){
            setTitle("테스트(점자->글자)");
        }
        total_number=it.getIntArrayExtra("total_number");
        incorrect_number=it.getIntArrayExtra("incorrect_number");
        incorrect_list = it.getIntegerArrayListExtra("incorrect_list");
        tv = (TextView)findViewById(R.id.recommend);
        calculateMax();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("resultCode : ",Integer.toString(resultCode));
        if(data!=null)
            Log.v("data : ","is not null");
        if(resultCode==RESULT_OK) {
            total_number = data.getIntArrayExtra("total_number");
            incorrect_number = data.getIntArrayExtra("incorrect_number");
            incorrect_list = data.getIntegerArrayListExtra("incorrect_list");
            for (int i = 0; i < 7; i++) {
                Log.v("TestActivitytotal_number" + i + " : ", Integer.toString(total_number[i]));
                Log.v("TestActivityincorrect_number" + i + " : ", Integer.toString(incorrect_number[i]));
            }
            for (int i = 0; i < incorrect_list.size(); i++)
                Log.v("Testincorrect_list"+i+" : ", Integer.toString(incorrect_list.get(i)));

            calculateMax();

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
                Log.v("TestMainSetResult","ok");

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//뒤로가기버튼누를시
    public void onBackPressed() {
        Intent it = new Intent();
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        setResult(RESULT_OK, it);
        Log.v("TestMainSetResult","ok");

        finish();
    }


    public void testConsonant(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",0);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);

        startActivityForResult(it,2);

        //startActivity(it);

    }

    public void testVowel(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",1);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);


       // startActivity(it);

    }

    public void testAbbreviation(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",2);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

     //   startActivity(it);
    }

    public void testAlphabet(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",3);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

       // startActivity(it);
    }

    public void testNumber(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",4);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

        //startActivityForResult(it,2);
//        startActivity(it);
    }

    public void testSymbol(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",5);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

       // startActivity(it);
    }

    public void testWord(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",6);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

       // startActivity(it);


    }

    public void testRandom(View v){
        Intent it;

        if(flag==1)
            it = new Intent(TestActivity.this,TestContentActivity.class);
        else
            it=new Intent(TestActivity.this,TestContentActivity2.class);

        it.putExtra("case",7);
        it.putExtra("count",1);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

       // startActivity(it);
    }

    public void GoBack(View v){
        finish();
    }

    public void clickDetail(View v){
        Intent it = new Intent(this,WrongAnswerActivity.class);
        it.putExtra("total_number",total_number);
        it.putExtra("incorrect_number",incorrect_number);
        it.putIntegerArrayListExtra("incorrect_list", (ArrayList<Integer>) incorrect_list);
        startActivityForResult(it,2);

        //startActivityForResult(it,2);//혹시나 초기화할 경우를 대비.
    }

    public void calculateMax(){

        double max=0;
        int check=0;
        for(int i=0;i<7;i++){
            if(total_number[i]!=0) {
                double temp;
                temp = (double)incorrect_number[i] / total_number[i];
                percent[i] = temp;
                if(max<temp){
                    max = temp;
                    check = i;
                }

            }
        }
        switch(check){
            case 0:
                tv.setText("한글 자음");
                break;
            case 1:
                tv.setText("한글 모음");
                break;
            case 2:
                tv.setText("한글 약어");
                break;
            case 3:
                tv.setText("알파벳");
                break;
            case 4:
                tv.setText("숫자");
                break;
            case 5:
                tv.setText("문장부호");
                break;
            case 6:
                tv.setText("단어");
                break;
        }
        tv.append("이(가) 취약합니다\n학습을 권장합니다.");
    }


}
