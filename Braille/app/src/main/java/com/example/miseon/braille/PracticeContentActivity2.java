package com.example.miseon.braille;

//여기서는 문장부호만 처리합니다.

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PracticeContentActivity2 extends AppCompatActivity{
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
    int flag31=7;
    int flag32=7;
    int flag33=7;
    int flag34=7;
    int flag35=7;
    int flag36=7;
    Vibrator mVibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_content);
        setTitle("점자연습-문장부호");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
            case "5":
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

            case "31":
                if(flag31==7){//흰->검일경우
                    flag31=1;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag31=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "32":
                if(flag32==7){//흰->검일경우
                    flag32=2;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag32=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "33":
                if(flag33==7){//흰->검일경우
                    flag33=3;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag33=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "34":
                if(flag34==7){//흰->검일경우
                    flag34=4;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag34=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "35":
                if(flag35==7){//흰->검일경우
                    flag35=5;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag35=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "36":
                if(flag36==7){//흰->검일경우
                    flag36=6;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag36=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
        }
        mVibe.vibrate(50);//0.05초
    }

    public void checkAnswer(View v){

    }

    public void hint(View v){

    }

}



