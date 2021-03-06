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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity4 extends AppCompatActivity{

    int flag1=7;
    int flag2=7;
    int flag3=7;
    int flag4=7;
    int flag5=7;
    int flag6=7;
    Vibrator mVibe;
    ImageView currentIv;
    int ivId=-1;    //input배열의 index로도 사용,배열에서 가장 마지막 입력값을 가리키는중.
    int[] input = new int[70];  //입력된 점자 정보를 저장. space일땐 0 그외엔 6자리 숫자가 저장.

    int totalWidth=0;


    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    int dot_num;
    String letter;

    TextView translateToWord;
    // TextView resultchang;

    Button add;
    Button change;
    Button reset;

    TextView inputwindow;
    TextView outputwindow;

    int happy1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate4);
        setTitle("변환(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        translateToWord=(TextView)findViewById(R.id.translateToWord);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_translate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
            case R.id.translateHangul:
                it=new Intent(this,TranslateActivity2.class);
                startActivity(it);
                finish();
                break;
            case R.id.translateEnglish:

                break;
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
        mVibe.vibrate(50);//0.05초
    }

    public void clickAdd(View v){
        int num = flag1*100000+flag2*10000+flag3*1000+flag4*100+flag5*10+flag6;
        LinearLayout layout=(LinearLayout) findViewById(R.id.input1);

        if(totalWidth>300)
            layout = (LinearLayout) findViewById(R.id.input2);
        if(totalWidth>=660) {
            Toast toast = Toast.makeText(this, "더이상 추가할 수 없습니다.", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            toast.show();
            return;
        }

        ImageView iv = new ImageView(this);
        currentIv = iv;
        Resources res = getResources();
        String str = "c" + num;
        int id_img = res.getIdentifier(str, "drawable", getPackageName());
        iv.setImageResource(id_img);
        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());//30dp
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());//50dp
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.
        //  layoutParams.rightMargin=2;
        layoutParams.gravity = Gravity.CENTER;
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ivId++;
        input[ivId]=num;
        iv.setId(ivId);
        layout.addView(iv);

        totalWidth += 30;
        initialization();
        mVibe.vibrate(50);//0.05초


    }

    public void clear(View v) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.input1);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout.removeAllViews();
        if(totalWidth>330){
            layout = (LinearLayout)findViewById(R.id.input2);
            inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout.removeAllViews();
        }
        ivId=-1;
        totalWidth=0;
        mVibe.vibrate(50);//0.05초
        translateToWord.setText("");

        initialization();


    }

    public void clickSpace(View v){
        LinearLayout layout = (LinearLayout)findViewById(R.id.input1);
        if(totalWidth>320)
            layout = (LinearLayout) findViewById(R.id.input2);
        if(totalWidth>=660) {
            Toast toast = Toast.makeText(this, "더이상 추가할 수 없습니다.", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            toast.show();
            return;
        }

        ImageView iv = new ImageView(this);
        Resources res = getResources();
        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.gravity = Gravity.CENTER;
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ivId++;
        input[ivId]=0;
        totalWidth+=10;
        iv.setId(ivId);
        layout.addView(iv);
        mVibe.vibrate(50);//0.05초

    }

    public void clickDel(View v){
        LinearLayout layout = (LinearLayout) findViewById(R.id.input1);

        if(totalWidth>330)
            layout = (LinearLayout)findViewById(R.id.input2);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(ivId!=-1){
            ImageView im = (ImageView)findViewById(ivId);
            layout.removeView(im);
            if(input[ivId]==0)
                totalWidth-=10;
            else
                totalWidth-=30;

            ivId--;
        }
        mVibe.vibrate(50);//0.05초
    }





    public void clickTranslate(View v){
        mVibe.vibrate(50);//0.05초

        String[] jeomjanum=new String[30];
        String wordText="";
        translateToWord.setText("");
        int count=0;
        int start=0;
        happy1=0;



        char[] chos_2 = new char[20];
        char[] juns_2 = new char[20];
        char[] jong_2 = new char[20];



        for(start=0; start<ivId+1; start++) {
            jeomjanum[start]= "c"+input[start];
        }


        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();

            Cursor cursor;

            for (start=0; start<ivId+1; start++) {
                if (jeomjanum[start].equals("c777776")) {//대문자 처리부분
                    cursor = sqlitedb.query("Braille", null, "dot_2=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "대문자"}, null, null, "letter");
                    if(cursor.moveToNext()) {
                        letter = cursor.getString(cursor.getColumnIndex("letter"));
                        wordText+=letter;
                    }
                    else {
                        happy1=1;
                        break;
                    }
                    start++;
                }
                else if (jeomjanum[start].equals("c0")) {
                    wordText+=" ";
                }
                else{

                    cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "소문자"}, null, null, "letter");
                    if(cursor.moveToNext()) {
                        letter = cursor.getString(cursor.getColumnIndex("letter"));
                        wordText+=letter;
                    }
                    else {
                        happy1=1;
                        break;
                    }

                }

            }

            sqlitedb.setTransactionSuccessful();

        }catch(SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
            sqlitedb.endTransaction();
            //     resultchang.setText("[변환 결과]");
            if (happy1==1) {
                Toast toast = Toast.makeText(this, "No Result", Toast.LENGTH_LONG);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                toast.show();
            }
            else translateToWord.setText(wordText);

        }

    }

    public void initialization(){

        Resources res = getResources();

        flag1 = flag2 = flag3 = flag4 = flag5 = flag6 = 7;
        Drawable drawable = res.getDrawable(R.drawable.shape_dot);
        Button butn = (Button) findViewById(R.id.dot1);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot2);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot3);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot4);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot5);
        butn.setBackgroundDrawable(drawable);
        butn = (Button) findViewById(R.id.dot6);
        butn.setBackgroundDrawable(drawable);


    }

}
