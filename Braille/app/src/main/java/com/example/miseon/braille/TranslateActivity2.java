package com.example.miseon.braille;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class TranslateActivity2 extends AppCompatActivity{
    int flag1=7;
    int flag2=7;
    int flag3=7;
    int flag4=7;
    int flag5=7;
    int flag6=7;
    Vibrator mVibe;
    ImageView currentIv;
    int ivId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate2);
        setTitle("변환(점자->한글)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);




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
            case "1":
                if(flag1==7){//흰->검일경우
                    flag1=1;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag1=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "2":
                if(flag2==7){//흰->검일경우
                    flag2=2;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag2=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "3":
                if(flag3==7){//흰->검일경우
                    flag3=3;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag3=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "4":
                if(flag4==7){//흰->검일경우
                    flag4=4;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag4=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "5":
                if(flag5==7){//흰->검일경우
                    flag5=5;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag5=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
            case "6":
                if(flag6==7){//흰->검일경우
                    flag6=6;
                    butn.setBackgroundDrawable(drawable1);
                }else{
                    flag6=7;
                    butn.setBackgroundDrawable(drawable2);
                }
                break;
        }
        mVibe.vibrate(50);
    }

    public void clickAdd(View v){
        int num = flag1*100000+flag2*10000+flag3*1000+flag4*100+flag5*10+flag6;
        LinearLayout layout = (LinearLayout)findViewById(R.id.input);
        ImageView iv = new ImageView(this);
        currentIv=iv;
        Resources res = getResources();
        String str = "c"+num;
        int id_img = res.getIdentifier(str,"drawable",getPackageName());
        iv.setImageResource(id_img);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60,90);
        layoutParams.rightMargin=5;
        layoutParams.gravity = Gravity.CENTER;
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ivId++;
        iv.setId(ivId);

       layout.addView(iv);

        flag1=flag2=flag3=flag4=flag5=flag6=7;
        Drawable drawable = res.getDrawable(R.drawable.shape_dot);
        Button butn=(Button)findViewById(R.id.dot1);
        butn.setBackgroundDrawable(drawable);
        butn=(Button)findViewById(R.id.dot2);
        butn.setBackgroundDrawable(drawable);
        butn=(Button)findViewById(R.id.dot3);
        butn.setBackgroundDrawable(drawable);
        butn=(Button)findViewById(R.id.dot4);
        butn.setBackgroundDrawable(drawable);
        butn=(Button)findViewById(R.id.dot5);
        butn.setBackgroundDrawable(drawable);
        butn=(Button)findViewById(R.id.dot6);
        butn.setBackgroundDrawable(drawable);

    }

    public void clear(View v) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.input);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout.removeAllViews();
        ivId=0;
    }

    public void clickSpace(View v){
        LinearLayout layout = (LinearLayout)findViewById(R.id.input);
        ImageView iv = new ImageView(this);
        Resources res = getResources();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,80);
        layoutParams.rightMargin=5;
        layoutParams.gravity = Gravity.CENTER;
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ivId++;
        iv.setId(ivId);
        layout.addView(iv);
    }

    public void clickDel(View v){
        LinearLayout layout = (LinearLayout) findViewById(R.id.input);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(ivId!=0){
            ImageView im = (ImageView)findViewById(ivId);
            layout.removeView(im);
            ivId--;
        }
    }


}

