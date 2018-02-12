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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TranslateActivity2 extends AppCompatActivity{
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


    char temp;
    int a;
    int b;
    int c;

    int count=0;
    int start;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate2);
        setTitle("변환(점자->글자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        translateToWord=(TextView)this.findViewById(R.id.translateToWord);
        translateToWord.setText("");


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
                // it=new Intent(this,TranslateActivity2.class);
                // startActivity(it);
                // finish();
                break;
            case R.id.translateEnglish:
                it=new Intent(this,TranslateActivity4.class);
                startActivity(it);
                finish();
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
            Toast.makeText(this, "더이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
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

        translateToWord.setText("");
        //      resultchang.setText("");


        mVibe.vibrate(50);//0.05초
        initialization();

    }

    public void clickSpace(View v){
        LinearLayout layout = (LinearLayout)findViewById(R.id.input1);
        if(totalWidth>320)
            layout = (LinearLayout) findViewById(R.id.input2);
        if(totalWidth>=660) {
            Toast.makeText(this, "더이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
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


        //Toast.makeText(this,"totalWidth = "+totalWidth,Toast.LENGTH_SHORT).show();
    }

    public void clickTranslate(View v){



        // ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ//19개
        final char[] CHO =
                {0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141,
                        0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
                        0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};

        //ㅏㅐㅑㅒㅓㅔㅕㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
        final char[] JUN =
                {0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155,//maybe 21개
                        0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b,
                        0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161,
                        0x3162, 0x3163};

        // ㄱㄲㄳㄴㄵㄶㄷㄹㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ
        final char[] JON =
                {0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136,//28개
                        0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d,
                        0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144,//  ㅂ은 0x3142
                        0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b,
                        0x314c, 0x314d, 0x314e};




        mVibe.vibrate(50);//0.05초

        String[] jeomjanum=new String[30];
        String wordText="";
        translateToWord.setText("");
//        int count=0;
        //     int start;
        count=0;

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
                //초성처리부분
                if (jeomjanum[start].equals("c777776")) {//
                    cursor = sqlitedb.query("Braille", null, "dot_2=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환초성"}, null, null, "letter");
                    if(cursor.moveToNext()) {
                        letter = cursor.getString(cursor.getColumnIndex("letter"));
                        chos_2[count]=letter.charAt(0);

                    }
                    start++;
                    start++;
                }
                else {
                    cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "변환초성"}, null, null, "letter");

                    if(cursor.moveToNext()) {
                        letter = cursor.getString(cursor.getColumnIndex("letter"));
                        chos_2[count]=letter.charAt(0);


                    }
                    start++;
                }


                // 중성처리부분
                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "중성"}, null, null, "letter");

                if(cursor.moveToNext()) {
                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                    juns_2[count]=letter.charAt(0);
                    start++;
                }



                //종성처리부분

                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "변환종성"}, null, null, "letter");

                if(cursor.moveToNext()) {
                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                    jong_2[count] = letter.charAt(0);
                    start++;
                    start--;
                }
                else {jong_2[count]=0x0000;start--;}

                //  start--;
                count++;

            }

            sqlitedb.setTransactionSuccessful();

        }catch(SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
            sqlitedb.endTransaction();

            String lastStr = "";



            for (int i = 0; i < count; i++) {

                if (chos_2[i] == 'ㄱ') {
                    a = 0;
                } else if (chos_2[i] == 'ㄲ') {
                    a = 1;
                } else if (chos_2[i] == 'ㄴ') {
                    a = 2;
                } else if (chos_2[i] == 'ㄷ') {
                    a = 3;
                } else if (chos_2[i] == 'ㄸ') {
                    a = 4;
                } else if (chos_2[i] == 'ㄹ') {
                    a = 5;
                } else if (chos_2[i] == 'ㅁ') {
                    a = 6;
                } else if (chos_2[i] == 'ㅂ') {
                    a = 7;
                } else if (chos_2[i] == 'ㅃ') {
                    a = 8;
                } else if (chos_2[i] == 'ㅅ') {
                    a = 9;
                } else if (chos_2[i] == 'ㅆ') {
                    a = 10;
                } else if (chos_2[i] == 'ㅇ') {
                    a = 11;
                } else if (chos_2[i] == 'ㅈ') {
                    a = 12;
                } else if (chos_2[i] == 'ㅉ') {
                    a = 13;
                } else if (chos_2[i] == 'ㅊ') {
                    a = 14;
                } else if (chos_2[i] == 'ㅋ') {
                    a = 15;
                } else if (chos_2[i] == 'ㅌ') {
                    a = 16;
                } else if (chos_2[i] == 'ㅍ') {
                    a = 17;
                } else if (chos_2[i] == 'ㅎ') {
                    a = 18;
                } else {
                    ;
                }

                //  ㅏㅐㅑㅒㅓㅔㅕㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
                if (juns_2[i] == 'ㅏ') {
                    b = 0;
                } else if (juns_2[i] == 'ㅐ') {
                    b = 1;
                } else if (juns_2[i] == 'ㅑ') {
                    b = 2;
                } else if (juns_2[i] == 'ㅒ') {
                    b = 3;
                } else if (juns_2[i] == 'ㅓ') {
                    b = 4;
                } else if (juns_2[i] == 'ㅔ') {
                    b = 5;
                } else if (juns_2[i] == 'ㅕ') {
                    b = 6;
                } else if (juns_2[i] == 'ㅖ') {
                    b = 7;
                } else if (juns_2[i] == 'ㅗ') {
                    b = 8;
                } else if (juns_2[i] == 'ㅘ') {
                    b = 9;
                } else if (juns_2[i] == 'ㅙ') {
                    b = 10;
                } else if (juns_2[i] == 'ㅚ') {
                    b = 11;
                } else if (juns_2[i] == 'ㅛ') {
                    b = 12;
                } else if (juns_2[i] == 'ㅜ') {
                    b = 13;
                } else if (juns_2[i] == 'ㅝ') {
                    b = 14;
                } else if (juns_2[i] == 'ㅞ') {
                    b = 15;
                } else if (juns_2[i] == 'ㅟ') {
                    b = 16;
                } else if (juns_2[i] == 'ㅠ') {
                    b = 17;
                } else if (juns_2[i] == 'ㅡ') {
                    b = 18;
                } else if (juns_2[i] == 'ㅢ') {
                    b = 19;
                } else if (juns_2[i] == 'l') {
                    b = 20;
                } else {
                    ;
                }


                if (jong_2[i] == 0x0000) {
                    c = 0;
                } else if (jong_2[i] == 'ㄱ') {
                    c = 1;
                } else if (jong_2[i] == 'ㄲ') {
                    c = 2;
                } else if (jong_2[i] == 'ㄳ') {
                    c = 3;
                } else if (jong_2[i] == 'ㄴ') {
                    c = 4;
                } else if (jong_2[i] == 'ㄵ') {
                    c = 5;
                } else if (jong_2[i] == 'ㄶ') {
                    c = 6;
                } else if (jong_2[i] == 'ㄷ') {
                    c = 7;
                } else if (jong_2[i] == 'ㄹ') {
                    c = 8;
                } else if (jong_2[i] == 'ㄺ') {
                    c = 9;
                } else if (jong_2[i] == 'ㄻ') {
                    c = 10;
                } else if (jong_2[i] == 'ㄼ') {
                    c = 11;
                } else if (jong_2[i] == 'ㄽ') {
                    c = 12;
                } else if (jong_2[i] == 'ㄾ') {
                    c = 13;
                } else if (jong_2[i] == 'ㄿ') {
                    c = 14;
                } else if (jong_2[i] == 'ㅀ') {
                    c = 15;
                } else if (jong_2[i] == 'ㅁ') {
                    c = 16;
                } else if (jong_2[i] == 'ㅂ') {
                    c = 17;
                } else if (jong_2[i] == 'ㅄ') {
                    c = 18;
                } else if (jong_2[i] == 'ㅅ') {
                    c = 19;
                } else if (jong_2[i] == 'ㅆ') {
                    c = 20;
                } else if (jong_2[i] == 'ㅇ') {
                    c = 21;
                } else if (jong_2[i] == 'ㅈ') {
                    c = 22;
                } else if (jong_2[i] == 'ㅊ') {
                    c = 23;
                } else if (jong_2[i] == 'ㅋ') {
                    c = 24;
                } else if (jong_2[i] == 'ㅌ') {
                    c = 25;
                } else if (jong_2[i] == 'ㅍ') {
                    c = 26;
                } else if (jong_2[i] == 'ㅎ') {
                    c = 27;
                }


                temp = (char) (0xAC00 + 28 * 21 * a + 28 * b + c);

                lastStr = lastStr.concat(String.valueOf(temp));

            }

            translateToWord.setText(lastStr);
            initialization();

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

