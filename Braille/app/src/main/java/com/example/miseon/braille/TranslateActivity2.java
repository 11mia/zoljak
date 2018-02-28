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

    int happy;
    int happy1;



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


        happy=0;
        happy1=0;




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

                if (jeomjanum[start].equals("c0")) {chos_2[count]=' ';juns_2[count]=' '; jong_2[count]=' '; count++;}//스페이스바 처리부분

                else {


                    if((start+2<ivId+1)&&ivId!=0&&jeomjanum[start].equals("c177457")&&jeomjanum[start+1].equals("c127776")&&jeomjanum[start+2].equals("c773477")) {//팠일 경우
                        chos_2[count]='ㅍ';
                        juns_2[count]='ㅏ';
                        jong_2[count]='ㅆ';

                        start++;
                        start++;
                        count++;

                    }
                    else if(ivId==0&&start==0&&jeomjanum[start].equals(("c777776"))||((ivId==start)&&jeomjanum[start].equals("c777776"))) {
                        chos_2[count]='b';
                        juns_2[count]='b';
                        jong_2[count]='b';
                        count++;
                        happy=1;
                        break;

                    }

                    else if(ivId!=0&&(start+1<ivId+1)&&jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c127456")) {//제16항 '성'일 경우
                        chos_2[count]='ㅅ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅇ';
                        start++;
                        count++;
                    }

                    else if (ivId!=0&&(start+2<ivId+1)&&jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c777776")&&jeomjanum[start+2].equals("c127456")) {//제16항 '썽'일 경우
                        chos_2[count]='ㅆ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅇ';
                        start++;
                        start++;
                        count++;
                    }

                    else if((start+1<ivId+1)&&ivId!=0&&jeomjanum[start].equals("c777476")&&jeomjanum[start+1].equals("c127456")) {//제 16항 '정'일 경우
                        chos_2[count]='ㅈ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅇ';
                        start++;
                        count++;
                    }


                    else if ((start+2<ivId+1)&&ivId!=0&&jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c777476")&&jeomjanum[start+2].equals("c127456")) {//제16항 '쩡'일 경우
                        chos_2[count]='ㅉ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅇ';
                        start++;
                        start++;
                        count++;
                    }

                    else if((start+1<ivId+1)&&ivId!=0&&jeomjanum[start].equals("c777756")&&jeomjanum[start+1].equals("c127456")) {//제 16항 '청'일 경우
                        chos_2[count]='ㅊ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅇ';
                        start++;
                        count++;
                    }



                    else if ((start+1==ivId)&&jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")//따빠짜약어
                            ||jeomjanum[start+1].equals("c777476"))) {
                        if( start+1<ivId+1) {
                            if (jeomjanum[start + 1].equals("c727477")) {//따
                                chos_2[count] = 'ㄸ';
                                juns_2[count] = 'ㅏ';
                            } else if (jeomjanum[start + 1].equals("c777457")) {//빠
                                chos_2[count] = 'ㅃ';
                                juns_2[count] = 'ㅏ';
                            } else if (jeomjanum[start + 1].equals("c777476")) {//따
                                chos_2[count] = 'ㅉ';
                                juns_2[count] = 'ㅏ';
                            }

                        }

                        jong_2[count]=0x0000;
                        start++;
                        count++;


                    }
                    else if (jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")//따빠짜약어
                            ||jeomjanum[start+1].equals("c777476"))&&jeomjanum[start+2].equals("c127776")&&(start+2<ivId+1)) {//따영 따이 짜여
                        if( start+3<ivId+1) {
                           cursor= sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 3]), "중성"}, null, null, "letter");

                           if(cursor.moveToNext()) {
                               if (jeomjanum[start + 1].equals("c727477")) {//따
                                   chos_2[count] = 'ㄸ';
                                   juns_2[count] = 'ㅏ';
                                   jong_2[count] = 0x0000;
                               } else if (jeomjanum[start + 1].equals("c777457")) {//빠
                                   chos_2[count] = 'ㅃ';
                                   juns_2[count] = 'ㅏ';
                                   jong_2[count] = 0x0000;
                               } else if (jeomjanum[start + 1].equals("c777476")) {//따
                                   chos_2[count] = 'ㅉ';
                                   juns_2[count] = 'ㅏ';
                                   jong_2[count] = 0x0000;
                               }

                           }
                           else{happy=1; count++; break;}

                        }
                        else { happy=1; count++;break;}


                        start++;
                        start++;
                        count++;


                    }








                    else if(jeomjanum[start].equals(("c773776"))) {

                        ;//붙임표 넘겨 그냥!제 5절 모음 연쇄 제 10항 제 11항
                    }

                    else if (((start==0)&&(ivId==0)&&jeomjanum[start].equals("c777776"))||(start==ivId&&jeomjanum[start].equals("c777776"))) {
                        happy=1;
                        chos_2[count]='b';
                        juns_2[count]='b';
                        jong_2[count]='b';
                        count++;
                        break;
                    }



                    else if(((ivId==0)&&jeomjanum[start].equals("c177477"))||((start==ivId)&&jeomjanum[start].equals("c177477"))) {//그냥 단어 딸랑 '나'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㄴ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }

                    else if((ivId==0&&jeomjanum[start].equals("c727477"))||((start==ivId)&&jeomjanum[start].equals("c727477"))) {//그냥 단어 딸랑 '다'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㄷ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }

                    else if((ivId==0&&jeomjanum[start].equals("c177757"))||((start==ivId)&&jeomjanum[start].equals("c177757"))) {//그냥 단어 딸랑 '마'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅁ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }

                    else if((ivId==0&&jeomjanum[start].equals("c777457"))||((start==ivId)&&jeomjanum[start].equals("c777457"))) {//그냥 단어 딸랑 '바'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅂ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }

                    else if((ivId==0&&jeomjanum[start].equals("c777476"))||((start==ivId)&&jeomjanum[start].equals("c777476"))) {//그냥 단어 딸랑 '자'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅈ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }
                    else if((ivId==0&&jeomjanum[start].equals("c127477"))||((start==ivId)&&jeomjanum[start].equals("c127477"))) {//그냥 단어 딸랑 '카'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅋ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }
                    else if((ivId==0&&jeomjanum[start].equals("c127757"))||((start==ivId)&&jeomjanum[start].equals("c127757"))) {//그냥 단어 딸랑 '타'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅌ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }
                    else if((ivId==0&&jeomjanum[start].equals("c177457"))||((start==ivId)&&jeomjanum[start].equals("c177457"))) {//그냥 단어 딸랑 '파'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅍ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }
                    else if((ivId==0&&jeomjanum[start].equals("c727457"))||((start==ivId)&&jeomjanum[start].equals("c727457"))) {//그냥 단어 딸랑 '하'이거 하나 점자 입력받은 경우

                        chos_2[count] = 'ㅎ';
                        juns_2[count] = 'ㅏ';
                        jong_2[count] = 0x0000;
                        count++;

                    }







                    else if (jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c127476")&&(start+1<ivId+1)) {//까
                        chos_2[count] = 'ㄲ';
                        juns_2[count] = 'ㅏ';





                        if (start + 2< ivId + 1) {//받침있는 경우




                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start+3<ivId+1)&&((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 3].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 3].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            }
                            else if (jeomjanum[start + 2].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else jong_2[count] = 0x0000;
                        } else jong_2[count] = 0x0000;



                        start++;
                        count++;

                    }

                    else if (jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c123777")&&(start+1<ivId+1)) {//까
                        chos_2[count]='ㅆ'; juns_2[count]='ㅏ';
                        if (start + 2< ivId + 1) {//받침있는 경우




                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start+3<ivId+1)&&((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 3].equals("c773777"))
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")))) {


                                    if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 3].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            }
                            else if (jeomjanum[start + 2].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else jong_2[count] = 0x0000;
                        }
                        else jong_2[count]=0x0000;

                        start++;
                        count++;

                    }

                    else if ((start+2<ivId+1)&&jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals(("c777456"))&&jeomjanum[start+2].equals("c723477")) {//껏
                        chos_2[count]='ㄲ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅅ';

                        start++;
                        start++;
                        count++;
                    }





                    else if(jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c777477")||jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")||jeomjanum[start+1].equals("c777776")||jeomjanum[start+1].equals(("c777476")))
                            &&( (jeomjanum[start+2].equals("c177456")||jeomjanum[start+2].equals("c723456")||jeomjanum[start+2].equals("c723457")||jeomjanum[start+2].equals("c177776")||
                            jeomjanum[start+2].equals("c127756")||jeomjanum[start+2].equals("c127456")||jeomjanum[start+2].equals("c173476")||jeomjanum[start+2].equals("c123756")||jeomjanum[start+2].equals("c123456")||
                            jeomjanum[start+2].equals("c127457")||jeomjanum[start+2].equals("c123476")||jeomjanum[start+2].equals("c173756")||jeomjanum[start+2].equals("c723476")||
                            jeomjanum[start+2].equals("c123457")))&&(start+2<ivId+1)) {

                        if (jeomjanum[start+1].equals("c777477")&&(start+1<ivId+1)) {
                            chos_2[count]='ㄲ';
                        }
                        else if (jeomjanum[start+1].equals("c727477")&&(start+1<ivId+1)) {
                            chos_2[count]='ㄸ';
                        }
                        else if (jeomjanum[start+1].equals("c777457")&&(start+1<ivId+1)) {
                            chos_2[count]='ㅃ';
                        }
                        else if (jeomjanum[start+1].equals("c777776")&&(start+1<ivId+1)) {
                            chos_2[count]='ㅆ';
                        }
                        else if (jeomjanum[start+1].equals("c777476")&&(start+1<ivId+1)) {
                            chos_2[count]='ㅉ';
                        }

                        if(start+2<ivId+1) {
                            if (jeomjanum[start + 2].equals("c177456") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅓ';
                                jong_2[count] = 'ㄱ';

                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        juns_2[count] = 'ㅓ';
                                        jong_2[count] = 'ㄲ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        juns_2[count] = 'ㅓ';
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            }
                            else if (jeomjanum[start + 2].equals("c723456") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅓ';
                                jong_2[count] = 'ㄴ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        juns_2[count] = 'ㅓ';
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        juns_2[count] = 'ㅓ';
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }


                            } else if (jeomjanum[start + 2].equals("c723457") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅓ';
                                jong_2[count] = 'ㄹ';

                                if ((start + 3 < ivId + 1)) {

                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c127777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c723776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }


                            } else if (jeomjanum[start + 2].equals("c177776") && (start + 2 < ivId + 1)) {//연
                                juns_2[count] = 'ㅕ';
                                jong_2[count] = 'ㄴ';

                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }

                            } else if (jeomjanum[start + 2].equals("c127756") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅕ';
                                jong_2[count] = 'ㄹ';

                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c127777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c723776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            } else if (jeomjanum[start + 2].equals("c177456") && (start + 2 < ivId + 1)) {
                                if (jeomjanum[start + 1].equals("c777776") || jeomjanum[start + 1].equals("c777476") && (start + 1 < ivId + 1)) {//성정청일 경우
                                    juns_2[count] = 'ㅓ';
                                    jong_2[count] = 'ㅇ';
                                } else {
                                    juns_2[count] = 'ㅕ';
                                    jong_2[count] = 'ㅇ';
                                }
                            } else if (jeomjanum[start + 2].equals("c173476") && (start + 2 < ivId + 1)) {//옥
                                juns_2[count] = 'ㅗ';
                                jong_2[count] = 'ㄱ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄲ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            } else if (jeomjanum[start + 2].equals("c123756") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅗ';
                                jong_2[count] = 'ㄴ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }

                            } else if (jeomjanum[start + 2].equals("c123456") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅗ';
                                jong_2[count] = 'ㅇ';
                            } else if (jeomjanum[start + 2].equals("c127457") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅜ';
                                jong_2[count] = 'ㄴ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            } else if (jeomjanum[start + 2].equals("c123476") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅜ';
                                jong_2[count] = 'ㄹ';

                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c127777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c723776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            } else if (jeomjanum[start + 2].equals("c173756") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅡ';
                                jong_2[count] = 'ㄴ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }

                            } else if (jeomjanum[start + 2].equals("c723476") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅡ';
                                jong_2[count] = 'ㄹ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c177777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c127777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c723776") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c727756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }
                            } else if (jeomjanum[start + 2].equals("c123457") && (start + 2 < ivId + 1)) {
                                juns_2[count] = 'ㅣ';
                                jong_2[count] = 'ㄴ';
                                if ((start + 3 < ivId + 1)) {
                                    if (jeomjanum[start + 3].equals("c173777") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (jeomjanum[start + 3].equals("c773756") && (start + 3 < ivId + 1)) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else {
                                        ;
                                    }
                                }

                            }


                        }
                        else {
                            happy=1; chos_2[count]='b'; juns_2[count]='b'; jong_2[count]='b';count++; break;}

                        start++;
                        start++;
                        count++;

                    }


                    else if((start+1<ivId+1)&&(jeomjanum[start].equals("c777477")||jeomjanum[start].equals("c177477")||jeomjanum[start].equals("c727477")||jeomjanum[start].equals("c777757")||//기본 초성에 약자 붙는거 제 6절 제 12항 관련
                            jeomjanum[start].equals("c177757")||jeomjanum[start].equals("c777457")||jeomjanum[start].equals("c777776")||jeomjanum[start].equals("c777476")||
                            jeomjanum[start].equals("c777756")||jeomjanum[start].equals("c127477")||jeomjanum[start].equals("c127757")||jeomjanum[start].equals("c177457")||
                            jeomjanum[start].equals("c727457"))
                            &&(jeomjanum[start+1].equals("c177456")||jeomjanum[start+1].equals("c723456")||jeomjanum[start+1].equals("c723457")||jeomjanum[start+1].equals("c177776")||
                            jeomjanum[start+1].equals("c127756")||jeomjanum[start+1].equals("c127456")||jeomjanum[start+1].equals("c173476")||jeomjanum[start+1].equals("c123756")||jeomjanum[start+1].equals("c123456")||
                            jeomjanum[start+1].equals("c127457")||jeomjanum[start+1].equals("c123476")||jeomjanum[start+1].equals("c173756")||jeomjanum[start+1].equals("c723476")||
                            jeomjanum[start+1].equals("c123457"))) {

                        if (jeomjanum[start].equals("c777477")) {
                            chos_2[count]='ㄱ';
                        }
                        else if (jeomjanum[start].equals("c177477")) {
                            chos_2[count]='ㄴ';
                        }
                        else if (jeomjanum[start].equals("c727477")) {
                            chos_2[count]='ㄷ';
                        }
                        else if (jeomjanum[start].equals("c777757")) {
                            chos_2[count]='ㄹ';
                        }
                        else if (jeomjanum[start].equals("c177757")) {
                            chos_2[count]='ㅁ';
                        }
                        else if (jeomjanum[start].equals("c777457")) {
                            chos_2[count]='ㅂ';
                        }
                        else if (jeomjanum[start].equals("c777776")) {
                            chos_2[count]='ㅅ';
                        }
                        else if (jeomjanum[start].equals("c777476")) {
                            chos_2[count]='ㅈ';
                        }
                        else if (jeomjanum[start].equals("c777756")) {
                            chos_2[count]='ㅊ';
                        }
                        else if (jeomjanum[start].equals("c127477")) {
                            chos_2[count]='ㅋ';
                        }
                        else if (jeomjanum[start].equals("c127757")) {
                            chos_2[count]='ㅌ';
                        }
                        else if (jeomjanum[start].equals("c177457")) {
                            chos_2[count]='ㅍ';
                        }
                        else if (jeomjanum[start].equals("c727457")) {
                            chos_2[count]='ㅎ';
                        }
                        else {;}



                        if(jeomjanum[start+1].equals("c177456")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅓ';
                            jong_2[count]='ㄱ';

                            if((start+2<ivId+1)) {
                                if(jeomjanum[start+2].equals("c177777")&&(start+2<ivId+1)) {
                                    jong_2[count]='ㄲ';
                                    start++;
                                }
                                else if( jeomjanum[start+2].equals("c773777")&&(start+2<ivId+1)) {
                                    jong_2[count]='ㄳ';
                                    start++;
                                }
                                else{;}
                            }
                        }
                        else if(jeomjanum[start+1].equals("c723456")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅓ';
                            jong_2[count]='ㄴ';

                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c173777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }

                        }
                        else if(jeomjanum[start+1].equals("c723457")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅓ';
                            jong_2[count]='ㄹ';

                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c177777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c127777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c723776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }

                        }



                        else if(jeomjanum[start+1].equals("c177776")&&(start+1<ivId+1)) {//연
                            juns_2[count]='ㅕ';
                            jong_2[count]='ㄴ';

                            if((start+2<ivId+1)) {
                                if(jeomjanum[start+2].equals("c173777")&&(start+2<ivId+1)) {
                                    jong_2[count]='ㄵ';
                                    start++;
                                }
                                else if( jeomjanum[start+2].equals("c773756")&&(start+2<ivId+1)) {
                                    jong_2[count]='ㄶ';
                                    start++;
                                }
                                else {;}

                            }
                        }
                        else if(jeomjanum[start+1].equals("c127756")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅕ';
                            jong_2[count]='ㄹ';


                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c177777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c127777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c723776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start+1].equals("c127456")&&(start+1<ivId+1)) {
                            if (jeomjanum[start].equals("c777776")||jeomjanum[start].equals("c777476")||jeomjanum[start].equals("c777756")) {//성정청일 경우
                                juns_2[count]='ㅓ';
                                jong_2[count]='ㅇ';
                            }
                            else {
                                juns_2[count] = 'ㅕ';
                                jong_2[count] = 'ㅇ';
                            }
                        }

                        else if(jeomjanum[start+1].equals("c173476")&&(start+1<ivId+1)) {//옥
                            juns_2[count]='ㅗ';
                            jong_2[count]='ㄱ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c177777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄲ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄳ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        else if(jeomjanum[start+1].equals("c123756")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅗ';
                            jong_2[count]='ㄴ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c173777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }


                        else if(jeomjanum[start+1].equals("c123456")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅗ';
                            jong_2[count]='ㅇ';
                        }
                        else if(jeomjanum[start+1].equals("c127457")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅜ';
                            jong_2[count]='ㄴ';

                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c173777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }

                        }
                        else if(jeomjanum[start+1].equals("c123476")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅜ';
                            jong_2[count]='ㄹ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c177777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c127777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c723776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }


                        else if(jeomjanum[start+1].equals("c173756")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅡ';
                            jong_2[count]='ㄴ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c173777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        else if(jeomjanum[start+1].equals("c723476")&&(start+1<ivId+1)) {
                            juns_2[count] = 'ㅡ';
                            jong_2[count] = 'ㄹ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c177777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c127777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c723776") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c727756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start+1].equals("c123457")&&(start+1<ivId+1)) {
                            juns_2[count]='ㅣ';
                            jong_2[count]='ㄴ';
                            if((start+2<ivId+1)) {
                                if (jeomjanum[start + 2].equals("c173777") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 2].equals("c773756") && (start + 2 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        start++;
                        count++;

                    }



                    else if((jeomjanum[start].equals("c177456")||jeomjanum[start].equals("c723456")||jeomjanum[start].equals("c723457")||jeomjanum[start].equals("c177776")||
                            jeomjanum[start].equals("c127756")||jeomjanum[start].equals("c127456")||jeomjanum[start].equals("c173476")||jeomjanum[start].equals("c123756")||jeomjanum[start].equals("c123456")||
                            jeomjanum[start].equals("c127457")||jeomjanum[start].equals("c123476")||jeomjanum[start].equals("c173756")||jeomjanum[start].equals("c723476")||
                            jeomjanum[start].equals("c123457"))) {

                        chos_2[count]='ㅇ';

                        if(jeomjanum[start].equals("c177456")) {
                            juns_2[count]='ㅓ';
                            jong_2[count]='ㄱ';


                            if((start+1<ivId+1) ) {

                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    juns_2[count] = 'ㅓ';
                                    jong_2[count] = 'ㄲ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    juns_2[count] = 'ㅓ';
                                    jong_2[count] = 'ㄳ';
                                    start++;
                                }
                                else {;}
                            }

                        }
                        else if(jeomjanum[start].equals("c723456")) {
                            juns_2[count]='ㅓ';
                            jong_2[count]='ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                }
                                else{;}
                            }
                        }
                        else if(jeomjanum[start].equals("c723457")) {
                            juns_2[count] = 'ㅓ';
                            jong_2[count] = 'ㄹ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c127777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c723776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c177776")) {//연
                            juns_2[count]='ㅕ';
                            jong_2[count]='ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c127756")) {
                            juns_2[count] = 'ㅕ';
                            jong_2[count] = 'ㄹ';

                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c127777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c723776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        else if(jeomjanum[start].equals("c127456")) {

                            juns_2[count] = 'ㅕ';
                            jong_2[count] = 'ㅇ';

                        }

                        else if(jeomjanum[start].equals("c173476")) {//옥
                            juns_2[count] = 'ㅗ';
                            jong_2[count] = 'ㄱ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄲ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄳ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        else if(jeomjanum[start].equals("c123756")) {
                            juns_2[count] = 'ㅗ';
                            jong_2[count] = 'ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        else if(jeomjanum[start].equals("c123456")) {
                            juns_2[count]='ㅗ';
                            jong_2[count]='ㅇ';
                        }
                        else if(jeomjanum[start].equals("c127457")) {
                            juns_2[count] = 'ㅜ';
                            jong_2[count] = 'ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c123476")) {
                            juns_2[count] = 'ㅜ';
                            jong_2[count] = 'ㄹ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c127777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c723776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c173756")) {
                            juns_2[count] = 'ㅡ';
                            jong_2[count] = 'ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c723476")) {
                            juns_2[count] = 'ㅡ';
                            jong_2[count] = 'ㄹ';

                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c177777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄺ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄻ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c127777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄼ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄽ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c723776") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄾ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c727756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄿ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㅀ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }
                        else if(jeomjanum[start].equals("c123457")) {
                            juns_2[count] = 'ㅣ';
                            jong_2[count] = 'ㄴ';
                            if((start+1<ivId+1) ) {
                                if (jeomjanum[start + 1].equals("c173777") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄵ';
                                    start++;
                                } else if (jeomjanum[start + 1].equals("c773756") && (start + 1 < ivId + 1)) {
                                    jong_2[count] = 'ㄶ';
                                    start++;
                                } else {
                                    ;
                                }
                            }
                        }

                        count++;

                    }
                    //



                    else if(((ivId!=0)&&(start+1<ivId+1)&&(jeomjanum[start].equals("c727477")||jeomjanum[start].equals("c177477")||jeomjanum[start].equals("c177757")
                            ||jeomjanum[start].equals("c777457")||jeomjanum[start].equals("c777476")||jeomjanum[start].equals("c127477")||jeomjanum[start].equals("c127757")
                            ||jeomjanum[start].equals("c177457")||jeomjanum[start].equals("c727457")))) {


                        if(jeomjanum[start].equals("c727477")) {//ㄷ
                            chos_2[count] = 'ㄷ';
                        }
                        else if (jeomjanum[start].equals("c177477")) {
                            chos_2[count]='ㄴ';
                        }
                        else if (jeomjanum[start].equals("c177757")) {
                            chos_2[count]='ㅁ';
                        }
                        else if (jeomjanum[start].equals("c777457")) {
                            chos_2[count]='ㅂ';
                        }
                        else if (jeomjanum[start].equals("c777476")) {
                            chos_2[count]='ㅈ';
                        }
                        else if (jeomjanum[start].equals("c127477")) {
                            chos_2[count]='ㅋ';
                        }
                        else if (jeomjanum[start].equals("c127757")) {
                            chos_2[count]='ㅌ';
                        }
                        else if (jeomjanum[start].equals("c177457")) {
                            chos_2[count]='ㅍ';
                        }
                        else if (jeomjanum[start].equals("c727457")) {
                            chos_2[count]='ㅎ';
                        }

                        cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "중성"}, null, null, "letter");

                        if (cursor.moveToNext()&&!(jeomjanum[start+1].equals("c127776"))) {//중성 있으면ㅏ가 아니고





                            letter = cursor.getString((cursor.getColumnIndex("letter")));

                            juns_2[count] = letter.charAt(0);


                            if((start+2<ivId+1)&&jeomjanum[start+1].equals("c773457")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅒ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123776")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅙ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅞ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c173477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅟ';
                                start++;

                            }




                            /*if((start+2)<(ivId+1)) {//중성이 있고 종성 처리 부분
                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");
                                if (cursor.moveToNext()) {
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    jong_2[count] = letter.charAt(0);
                                    start++;
                                } else jong_2[count] = 0x0000;
                            }
                            else {
                                jong_2[count]=0x0000;
                            }*/
                            if (start + 2< ivId + 1) {//받침있는 경우



                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");
                                if (cursor.moveToNext()) {
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    jong_2[count] = letter.charAt(0);


                                    if ((start+3<ivId+1)&&((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                            (letter.equals("ㄹ")&& jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 3].equals("c773777")
                                            ||(letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777"))))) {

                                        if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                            jong_2[count] = 'ㄳ';
                                            start++;
                                        } else if (letter.equals("ㄴ")  && jeomjanum[start + 3].equals("c173777")) {
                                            jong_2[count] = 'ㄵ';
                                            start++;
                                        } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                            jong_2[count] = 'ㄶ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                            jong_2[count] = 'ㄺ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                            jong_2[count] = 'ㄻ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                            jong_2[count] = 'ㄼ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                            jong_2[count] = 'ㄽ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                            jong_2[count] = 'ㄾ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                            jong_2[count] = 'ㄿ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                            jong_2[count] = 'ㅀ';
                                            start++;
                                        } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                            jong_2[count] = 'ㅄ';
                                            start++;
                                        } else if (letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")) {
                                            jong_2[count]='ㄲ';
                                            start++;
                                        }


                                    } else {
                                        jong_2[count] = letter.charAt(0);
                                    }

                                    start++;

                                }
                                else if (jeomjanum[start + 2].equals("c773477")) {
                                    jong_2[count] = 'ㅆ';
                                    start++;

                                }
                                else jong_2[count] = 0x0000;
                            } else jong_2[count] = 0x0000;
                            start++;
                            count++;

                        } else {//중성 없으면
                            juns_2[count] = 'ㅏ';
                        /*    cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");

                           if (cursor.moveToNext()) {
                                letter = cursor.getString((cursor.getColumnIndex("letter")));
                                jong_2[count] = letter.charAt(0);
                                start++;

                            } else jong_2[count] = 0x0000;*/

                            if((start+2<ivId+1)&&jeomjanum[start+1].equals("c127776")) {

                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "중성"}, null, null, "letter");
                                if(cursor.moveToNext()) {
                                    juns_2[count]='ㅏ';
                                    jong_2[count]=0x0000;
                                    //  start++;
                                    //   count++;
                                }
                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "한글약어"}, null, null, "letter");
                                if(cursor.moveToNext()) {
                                    juns_2[count]='ㅏ';
                                    jong_2[count]=0x0000;
                                    //  start++;
                                    //   count++;
                                }
                                start++;

                            }


                            else  if (start + 1< ivId + 1) {




                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");
                                if (cursor.moveToNext()) {//받침있는 경우
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    jong_2[count] = letter.charAt(0);


                                    if ((start+2<ivId+1)&&((jeomjanum[start + 1].equals("c177777") && jeomjanum[start + 2].equals("c773777")) || (jeomjanum[start + 1].equals("c727757") && jeomjanum[start + 2].equals("c173777")) || (jeomjanum[start + 1].equals("727757") && jeomjanum[start + 2].equals("c773756")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c127777")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) ||
                                            (letter.equals("ㄹ")&& jeomjanum[start + 2].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 2].equals("c773777"))
                                            ||(letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777")))) {

                                        if (letter.equals("ㄱ") && jeomjanum[start + 2].equals("c773777")) {
                                            jong_2[count] = 'ㄳ';
                                            start++;
                                        } else if (letter.equals("ㄴ")  && jeomjanum[start + 2].equals("c173777")) {
                                            jong_2[count] = 'ㄵ';
                                            start++;
                                        } else if (letter.equals("ㄴ") && jeomjanum[start + 2].equals("c773756")) {
                                            jong_2[count] = 'ㄶ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) {
                                            jong_2[count] = 'ㄺ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) {
                                            jong_2[count] = 'ㄻ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c1127777")) {
                                            jong_2[count] = 'ㄼ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) {
                                            jong_2[count] = 'ㄽ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) {
                                            jong_2[count] = 'ㄾ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) {
                                            jong_2[count] = 'ㄿ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773756")) {
                                            jong_2[count] = 'ㅀ';
                                            start++;
                                        } else if (letter.equals("ㅂ") && jeomjanum[start + 2].equals("c773777")) {
                                            jong_2[count] = 'ㅄ';
                                            start++;
                                        }
                                        else if (letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777")) {
                                            jong_2[count]='ㄲ';
                                            start++;
                                        }


                                    }


                                    start++;

                                }
                                else if (jeomjanum[start + 1].equals("c773477")) {
                                    jong_2[count] = 'ㅆ';
                                    start++;

                                }

                                else {jong_2[count] = 0x0000;
                                    // start++;
                                }
                            } else {//받침 없는 경우

                                jong_2[count] = 0x0000;
                                //    start++;

                            }

                            count++;

                        }

                    }






                    else if (jeomjanum[start].equals("c177477")&&jeomjanum[start+1].equals("c127776")) {//제 17항 나에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㄴ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //    start++;
                        count++;

                    }

                    else if (jeomjanum[start].equals("c727477")&&jeomjanum[start+1].equals("c127776")) {//제 17항 다에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㄷ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //      start++;
                        count++;

                    }

                    else if (jeomjanum[start].equals("c177757")&&jeomjanum[start+1].equals("c127776")) {//제 17항 마에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅁ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //    start++;
                        count++;

                    }
                    else if (jeomjanum[start].equals("c777457")&&jeomjanum[start+1].equals("c127776")) {//제 17항 바에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅂ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

//                        start++;
                        count++;

                    }
                    else if (jeomjanum[start].equals("c777476")&&jeomjanum[start+1].equals("c127776")) {//제 17항 자에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅈ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //       start++;
                        count++;

                    }   else if (jeomjanum[start].equals("c127477")&&jeomjanum[start+1].equals("c127776")) {//제 17항 카에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅋ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        start++;
                        count++;

                    }   else if (jeomjanum[start].equals("c127757")&&jeomjanum[start+1].equals("c127776")) {//제 17항 타에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅌ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //         start++;
                        count++;

                    }   else if (jeomjanum[start].equals("c177457")&&jeomjanum[start+1].equals("c127776")) {//제 17항 파에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅍ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //          start++;
                        count++;

                    }   else if (jeomjanum[start].equals("c727457")&&jeomjanum[start+1].equals("c127776")) {//제 17항 하에 모음이 이어나올 때에는 ㅏ를 생략하지 않는다.
                        chos_2[count]='ㅎ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;

                        //               start++;
                        count++;

                    }



                    else if (jeomjanum[start].equals("c777456")&&jeomjanum[start+1].equals("c723477")&&(start+1<ivId+1)){//제 6절 제 12항 것
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅓ';
                        jong_2[count]='ㅅ';

                        start++;
                        count++;
                    }






                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c723477"))) {//약어 그래서1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅐ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅅ';
                        juns_2[count+2]='ㅓ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c723477"))) {//약어 그래서2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅐ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅅ';
                        juns_2[count+2]='ㅓ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;//그
                        count++;//래
                        count++;//서

                    }





                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c177477"))) {//약어 그러나1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㄴ';
                        juns_2[count+2]='ㅏ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c177477"))) {//약어 그러나2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㄴ';
                        juns_2[count+2]='ㅏ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;//그
                        count++;//러
                        count++;//나

                    }

                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c727757"))) {//약어 그러면1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅁ';
                        juns_2[count+2]='ㅕ';
                        jong_2[count+2]='ㄴ';

                        start++;

                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c727757"))) {//약어 그러면2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅁ';
                        juns_2[count+2]='ㅕ';
                        jong_2[count+2]='ㄴ';

                        start++;

                        count++;//그
                        count++;//러
                        count++;//면

                    }


                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c727776"))) {//약어 그러므로1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅁ';
                        juns_2[count+2]='ㅡ';
                        jong_2[count+2]=0x0000;

                        chos_2[count+3]='ㄹ';
                        juns_2[count+3]='ㅗ';
                        jong_2[count+3]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c727776"))) {//약어 그러므로2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅁ';
                        juns_2[count+2]='ㅡ';
                        jong_2[count+2]=0x0000;

                        chos_2[count+3]='ㄹ';
                        juns_2[count+3]='ㅗ';
                        jong_2[count+3]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;
                        count++;

                    }




                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c173457"))) {//약어 그런데1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]='ㄴ';

                        chos_2[count+2]='ㄷ';
                        juns_2[count+2]='ㅔ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c173457"))) {//약어 그런데2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅓ';
                        jong_2[count+1]='ㄴ';

                        chos_2[count+2]='ㄷ';
                        juns_2[count+2]='ㅔ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }

                    else if((start+1<ivId+2)&&(start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c173776")))) {//약어 그리고1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅣ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㄱ';
                        juns_2[count+2]='ㅗ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c173776"))) {//약어 그리고2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅣ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㄱ';
                        juns_2[count+2]='ㅗ';
                        jong_2[count+2]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;

                    }


                    else if((start+1<ivId+2)&&start>0&&jeomjanum[start-1].equals("c0")&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c177756"))) {//약어 그리하여1
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅣ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅎ';
                        juns_2[count+2]='ㅏ';
                        jong_2[count+2]=0x0000;

                        chos_2[count+3]='ㅇ';
                        juns_2[count+3]='ㅕ';
                        jong_2[count+3]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;
                        count++;

                    }
                    else if((start+1<ivId+1)&&start==0&&jeomjanum[start].equals("c177777")&&jeomjanum[start+1].equals(("c177756"))) {//약어 그리하여2
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅡ';
                        jong_2[count]=0x0000;

                        chos_2[count+1]='ㄹ';
                        juns_2[count+1]='ㅣ';
                        jong_2[count+1]=0x0000;

                        chos_2[count+2]='ㅎ';
                        juns_2[count+2]='ㅏ';
                        jong_2[count+2]=0x0000;

                        chos_2[count+3]='ㅇ';
                        juns_2[count+3]='ㅕ';
                        jong_2[count+3]=0x0000;

                        start++;

                        count++;
                        count++;
                        count++;
                        count++;

                    }

/////////////////////////////////////////////////////////


                    else if ((start+1==ivId)&&jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")//따빠짜약어
                            ||jeomjanum[start+1].equals("c777476"))) {
                       if( start+1<ivId+1) {
                           if (jeomjanum[start + 1].equals("c727477")) {//따
                               chos_2[count] = 'ㄸ';
                               juns_2[count] = 'ㅏ';
                           } else if (jeomjanum[start + 1].equals("c777457")) {//빠
                               chos_2[count] = 'ㅃ';
                               juns_2[count] = 'ㅏ';
                           } else if (jeomjanum[start + 1].equals("c777476")) {//따
                               chos_2[count] = 'ㅉ';
                               juns_2[count] = 'ㅏ';
                           }

                       }

                        jong_2[count]=0x0000;
                        start++;
                        count++;


                    }
//////////////////////////////////


                    else if (jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")//따빠짜약어
                            ||jeomjanum[start+1].equals("c777476"))&&(start+2<ivId+1)
                            &&!sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "중성"}, null, null, "letter").moveToNext()){
                        if(jeomjanum[start+1].equals("c727477")) {//따
                            chos_2[count]='ㄸ';
                            juns_2[count]='ㅏ';
                        }
                      else  if(jeomjanum[start+1].equals("c777457")) {//빠
                            chos_2[count]='ㅃ';
                            juns_2[count]='ㅏ';
                        }
                       else if(jeomjanum[start+1].equals("c777476")) {//따
                            chos_2[count]='ㅉ';
                            juns_2[count]='ㅏ';
                        }

                        cursor=sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");



                        if (start + 2< ivId + 1) {
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start + 3 < ivId + 1) && ((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")
                                        || (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    } else if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄲ';
                                        start++;
                                    }


                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            } else if (jeomjanum[start + 2].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            } else jong_2[count] = 0x0000;
                        }else jong_2[count]=0x0000;


                     //   start++;
                        start++;
                        count++;



















                    }













                    else if (!(jeomjanum[start].equals("c777776")&&jeomjanum[start+1].equals("c777456")&&jeomjanum[start+2].equals("c723477")&&(start+2==ivId)
                    )&&ivId!=0&&jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c777776")||jeomjanum[start+1].equals("c777477")||jeomjanum[start+1].equals("c727477")||
                            jeomjanum[start+1].equals("c777457")||jeomjanum[start+1].equals("c777476"))&&(start+1<ivId+1)&&ivId!=1&&!(jeomjanum[start+2].equals("c127776"))&&(start+2<ivId+1)) {//case1
                        cursor = sqlitedb.query("Braille", null, "dot_2=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환초성"}, null, null, "letter");


                        if (cursor.moveToNext()) {//된소리이면 case1_1
                            letter = cursor.getString(cursor.getColumnIndex("letter"));
                            chos_2[count] = letter.charAt(0);
                            // start++;


                            if (start + 2 < ivId + 1) {
                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "중성"}, null, null, "letter");

                                if (cursor.moveToNext()) {
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    juns_2[count] = letter.charAt(0);



                                    if((start+3<ivId+1)&&jeomjanum[start+2].equals("c773457")&&jeomjanum[start+3].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅒ';
                                        start++;

                                    }
                                    else if((start+3<ivId+1)&&jeomjanum[start+2].equals("c123776")&&jeomjanum[start+3].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅙ';
                                        start++;

                                    }
                                    else if((start+3<ivId+1)&&jeomjanum[start+2].equals("c123477")&&jeomjanum[start+3].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅞ';
                                        start++;

                                    }
                                    else if((start+3<ivId+1)&&jeomjanum[start+2].equals("c173477")&&jeomjanum[start+3].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅟ';
                                        start++;

                                    }


                                }

                                else {
                                    chos_2[count] = 'b';
                                    juns_2[count] = 'b';
                                    jong_2[count] = 'b';
                                    count++;
                                    break;
                                }
                            }
                         /*   else if (jeomjanum[start].equals("c777776")&&(jeomjanum[start+1].equals("c727477")||jeomjanum[start+1].equals("c777457")
                            ||jeomjanum[start+1].equals("c777476"))&&(start+1<ivId+1)){


                            }*/

                            else {
                                chos_2[count] = 'b';
                                juns_2[count] = 'b';
                                jong_2[count] = 'b';
                                count++;
                                break;
                            }




                      /*  if (start + 3 < ivId + 1) {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 3]), "종성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);
                                start++;

                            } else jong_2[count] = 0x0000;
                        } else jong_2[count] = 0x0000;*/


                            if (start + 3< ivId + 1) {//받침있는 경우




                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 3]), "변환종성"}, null, null, "letter");
                                if (cursor.moveToNext()) {
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    jong_2[count] = letter.charAt(0);


                                    if ((start+4<ivId+1)&&((jeomjanum[start + 3].equals("c177777") && jeomjanum[start + 4].equals("c773777")) || (jeomjanum[start + 3].equals("c727757") && jeomjanum[start + 4].equals("c173777")) || (jeomjanum[start + 3].equals("727757") && jeomjanum[start + 4].equals("c773756")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c127777")) ||
                                            (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c727756")) ||
                                            (letter.equals("ㄹ")&& jeomjanum[start + 4].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 4].equals("c773777")
                                            ||(letter.equals("ㄱ")&&jeomjanum[start+4].equals("c177777"))))) {

                                        if (letter.equals("ㄱ") && jeomjanum[start + 4].equals("c773777")) {
                                            jong_2[count] = 'ㄳ';
                                            start++;
                                        } else if (letter.equals("ㄴ")  && jeomjanum[start + 4].equals("c173777")) {
                                            jong_2[count] = 'ㄵ';
                                            start++;
                                        } else if (letter.equals("ㄴ") && jeomjanum[start + 4].equals("c773756")) {
                                            jong_2[count] = 'ㄶ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c177777")) {
                                            jong_2[count] = 'ㄺ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c727776")) {
                                            jong_2[count] = 'ㄻ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c1127777")) {
                                            jong_2[count] = 'ㄼ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c773777")) {
                                            jong_2[count] = 'ㄽ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c723776")) {
                                            jong_2[count] = 'ㄾ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c727756")) {
                                            jong_2[count] = 'ㄿ';
                                            start++;
                                        } else if (letter.equals("ㄹ") && jeomjanum[start + 4].equals("c773756")) {
                                            jong_2[count] = 'ㅀ';
                                            start++;
                                        } else if (letter.equals("ㅂ") && jeomjanum[start + 4].equals("c773777")) {
                                            jong_2[count] = 'ㅄ';
                                            start++;
                                        }
                                        else if (letter.equals("ㄱ")&&jeomjanum[start+4].equals("c177777")) {
                                            jong_2[count]='ㄲ';
                                            start++;
                                        }



                                    } else {
                                        jong_2[count] = letter.charAt(0);
                                    }


                                    start++;

                                }
                                else if (jeomjanum[start + 3].equals("c773477")) {
                                    jong_2[count] = 'ㅆ';
                                    start++;

                                }
                                else jong_2[count] = 0x0000;
                            } else jong_2[count] = 0x0000;

                            start++;
                            start++;
                            count++;


                        }


                    }

                    else if (ivId!=0&&jeomjanum[start].equals("c777776")&&(start+1<ivId+1)){//된소리가 아니고 그냥 ㅅ이면 case1_1

                        chos_2[count]='ㅅ';
                        cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start+1]), "중성"}, null, null, "letter");

                        if (cursor.moveToNext()) {
                            letter=cursor.getString(cursor.getColumnIndex("letter"));
                            juns_2[count]=letter.charAt(0);


                            if((start+2<ivId+1)&&jeomjanum[start+1].equals("c773457")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅒ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123776")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅙ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅞ';
                                start++;

                            }
                            else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c173477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                juns_2[count]='ㅟ';
                                start++;

                            }
                        }
                        else { chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b'; count++;break;}


/*
                        if(start+2<ivId+1) {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);
                                start++;
                            } else
                            {
                                jong_2[count]=0x0000;
                            }

                        }else jong_2[count]=0x0000;*/
                        if (start + 2< ivId + 1) {//받침있는 경우




                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start+3<ivId+1)&&((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 3].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 3].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            }
                            else if (jeomjanum[start + 2].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else jong_2[count] = 0x0000;
                        } else jong_2[count] = 0x0000;

                        start++;
                        count++;

                    }



                    else if((ivId==0&&jeomjanum[start].equals("c127476"))||((start==ivId)&&jeomjanum[start].equals("c127476"))) {
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;
                        count++;
                    }

                    else if(jeomjanum[start].equals("c127476")&&(start<ivId+1)) {//case2 약어 처리 약어 '가'인경우
                        chos_2[count]='ㄱ';
                        juns_2[count]='ㅏ';
                        /*if(start+1<ivId+1) {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);
                                start++;
                            } else jong_2[count] = 0x0000;

                        }
                        else jong_2[count]=0x0000;*/
                        if (start + 1< ivId + 1) {//받침있는 경우




                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start+2<ivId+1)&&((jeomjanum[start + 1].equals("c177777") && jeomjanum[start + 2].equals("c773777")) || (jeomjanum[start + 1].equals("c727757") && jeomjanum[start + 2].equals("c173777")) || (jeomjanum[start + 1].equals("727757") && jeomjanum[start + 2].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 2].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 2].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 2].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            }
                            else if (jeomjanum[start + 1].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else jong_2[count] = 0x0000;
                        } else jong_2[count] = 0x0000;

                        count++;


                    }//한단어 끝나요

                    else if ((ivId==0&&jeomjanum[start].equals(("c123777"))||(start==ivId)&&jeomjanum[start].equals("c123777"))) {
                        chos_2[count]='ㅅ';
                        juns_2[count]='ㅏ';
                        jong_2[count]=0x0000;
                        count++;
                    }

                    else if(jeomjanum[start].equals("c123777")&&(start<ivId+1)) {//case2 약어 처리 약어 '사'인경우
                        chos_2[count]='ㅅ';
                        juns_2[count]='ㅏ';
                       /* if(start+1<ivId+1) {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "종성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);
                                start++;
                            } else jong_2[count] = 0x0000;

                        }
                        else jong_2[count]=0x0000;*/
                        if (start + 1< ivId + 1) {//받침있는 경우




                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);


                                if ((start+2<ivId+1)&&((jeomjanum[start + 1].equals("c177777") && jeomjanum[start + 2].equals("c773777")) || (jeomjanum[start + 1].equals("c727757") && jeomjanum[start + 2].equals("c173777")) || (jeomjanum[start + 1].equals("727757") && jeomjanum[start + 2].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 2].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 2].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 2].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }


                                start++;

                            }
                            else if (jeomjanum[start + 1].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else jong_2[count] = 0x0000;
                        } else jong_2[count] = 0x0000;
                        count++;


                    }//한단어 끝나요(한단어 끝날때마다 count++ 이거 꼭 해주세요 바로 위에 코드 한 줄 보이죠??



                    else {//오리지널



                        cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "변환초성"}, null, null, "letter");

                        if (cursor.moveToNext()) {

                            if (start == 0 && ivId == 0) {
                                chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b';
                                happy = 1;
                                count++;
                                break;
                            }
                            else {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                chos_2[count] = letter.charAt(0);

                            }

                            //   start++;
                        }
                        else {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "중성"}, null, null, "letter");
                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                juns_2[count] = letter.charAt(0);
                                chos_2[count] = 'ㅇ';
                            } else {
                                happy = 1;
                                chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b';
                                count++;
                                break;

                            }

                        }//ㅇ없으면 chos_2[count]여기다가 ㅇ 저장


                        // 중성처리부분


                        if (chos_2[count] == 'ㅇ') {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start]), "중성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                juns_2[count] = letter.charAt(0);




                                if((start+1<ivId+1)&&jeomjanum[start].equals("c773457")&&jeomjanum[start+1].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                    juns_2[count]='ㅒ';
                                    start++;

                                }
                                else if((start+1<ivId+1)&&jeomjanum[start].equals("c123776")&&jeomjanum[start+1].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                    juns_2[count]='ㅙ';
                                    start++;

                                }
                                else if((start+1<ivId+1)&&jeomjanum[start].equals("c123477")&&jeomjanum[start+1].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                    juns_2[count]='ㅞ';
                                    start++;

                                }
                                else if((start+1<ivId+1)&&jeomjanum[start].equals("c173477")&&jeomjanum[start+1].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                    juns_2[count]='ㅟ';
                                    start++;

                                }










                                //      start++;
                            } else {
                                happy = 1;
                                chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b';
                                count++;
                                break;
                            }
                        } else {
                            if (start + 1 < ivId + 1) {
                                cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "중성"}, null, null, "letter");


                                if (cursor.moveToNext()) {
                                    letter = cursor.getString(cursor.getColumnIndex("letter"));
                                    juns_2[count] = letter.charAt(0);




                                    if((start+2<ivId+1)&&jeomjanum[start+1].equals("c773457")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅒ';
                                        start++;

                                    }
                                    else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123776")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅙ';
                                        start++;

                                    }
                                    else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c123477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅞ';
                                        start++;

                                    }
                                    else if((start+2<ivId+1)&&jeomjanum[start+1].equals("c173477")&&jeomjanum[start+2].equals("c123757")) {//중성에서 ㅒ, ㅙ, ㅞ, ㅟ 처리
                                        juns_2[count]='ㅟ';
                                        start++;

                                    }





                                    //      start++;
                                } else {
                                    happy = 1;
                                    chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b';
                                    count++;
                                    break;
                                }


                            } else {
                                happy = 1;
                                chos_2[count]='b';juns_2[count]='b'; jong_2[count]='b';
                                count++;
                                break;
                            }

                        }





                        //종성처리부분
                        if ((start + 1 < ivId+1) && (chos_2[count] == 'ㅇ')) {

                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 1]), "변환종성"}, null, null, "letter");

                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);







                                if ((start+2<ivId+1)&&((jeomjanum[start + 1].equals("c177777") && jeomjanum[start + 2].equals("c773777")) || (jeomjanum[start + 1].equals("c727757") && jeomjanum[start + 2].equals("c173777")) || (jeomjanum[start + 1].equals("727757") && jeomjanum[start + 2].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 2].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 2].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 2].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 2].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 2].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+2].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }








                                start++;

                            }
                            else if (jeomjanum[start + 1].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }else jong_2[count] = 0x0000;
                        }

                        else if( chos_2[count]=='ㅇ'&&(start==ivId)) {
                            jong_2[count]=0x0000;

                        }


                        else if ((start + 2 < ivId+1)) {
                            cursor = sqlitedb.query("Braille", null, "dot_1=?AND type=?", new String[]{String.valueOf(jeomjanum[start + 2]), "변환종성"}, null, null, "letter");


                            if (cursor.moveToNext()) {
                                letter = cursor.getString(cursor.getColumnIndex("letter"));
                                jong_2[count] = letter.charAt(0);









                                if ((start+3<ivId+1)&&((jeomjanum[start + 2].equals("c177777") && jeomjanum[start + 3].equals("c773777")) || (jeomjanum[start + 2].equals("c727757") && jeomjanum[start + 3].equals("c173777")) || (jeomjanum[start + 2].equals("727757") && jeomjanum[start + 3].equals("c773756")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c127777")) ||
                                        (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) || (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) ||
                                        (letter.equals("ㄹ")&& jeomjanum[start + 3].equals("c773756")) || (letter.equals("ㅂ")&& jeomjanum[start + 3].equals("c773777")
                                        ||(letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777"))))) {

                                    if (letter.equals("ㄱ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄳ';
                                        start++;
                                    } else if (letter.equals("ㄴ")  && jeomjanum[start + 3].equals("c173777")) {
                                        jong_2[count] = 'ㄵ';
                                        start++;
                                    } else if (letter.equals("ㄴ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㄶ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c177777")) {
                                        jong_2[count] = 'ㄺ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727776")) {
                                        jong_2[count] = 'ㄻ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c1127777")) {
                                        jong_2[count] = 'ㄼ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㄽ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c723776")) {
                                        jong_2[count] = 'ㄾ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c727756")) {
                                        jong_2[count] = 'ㄿ';
                                        start++;
                                    } else if (letter.equals("ㄹ") && jeomjanum[start + 3].equals("c773756")) {
                                        jong_2[count] = 'ㅀ';
                                        start++;
                                    } else if (letter.equals("ㅂ") && jeomjanum[start + 3].equals("c773777")) {
                                        jong_2[count] = 'ㅄ';
                                        start++;
                                    }
                                    else if (letter.equals("ㄱ")&&jeomjanum[start+3].equals("c177777")) {
                                        jong_2[count]='ㄲ';
                                        start++;
                                    }



                                } else {
                                    jong_2[count] = letter.charAt(0);
                                }





                                start++;
                                start++;
                            }
                            else if (jeomjanum[start + 2].equals("c773477")) {
                                jong_2[count] = 'ㅆ';
                                start++;

                            }
                            else {jong_2[count] = 0x0000;start++;}

                        }
                        else {jong_2[count]=0x0000; start++;}




                        count++;


                    }//기본적인 처리부분 끝나는것

                }//스페이스바아닌부분

            }

            sqlitedb.setTransactionSuccessful();

        }catch(/*SQLite*/Exception e){
            /*Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();*/
            happy1 = 1;
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
                } else if (chos_2[i]== ' ') {
                    ;
                }
                else {
                    happy=1;
                    break;
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
                } else if (juns_2[i] == 'ㅣ') {
                    b = 20;
                }
                else if (juns_2[i]== ' ') {
                    ;
                }else {
                    happy=1;
                    break;
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
                else if (jong_2[i]== ' ') {
                    ;
                }
                else {happy=1; break;}



                if (happy==1||juns_2[i]=='b'||chos_2[i]=='b'||jong_2[i]=='b') {  happy = 1;break;
                }
                else {
                    temp = (char) (0xAC00 + 28 * 21 * a + 28 * b + c);

                    if (chos_2[i] == ' ') {
                        lastStr = lastStr.concat((String.valueOf(' ')));
                    } else {
                        lastStr = lastStr.concat(String.valueOf(temp));
                    }

                }

            }


            if(happy1==1) {
                translateToWord.setText("");
                /*translateToWord.setText("-변환 결과가 없습니다-");*/
                Toast.makeText(this, "변환 결과가 없습니다.", Toast.LENGTH_LONG).show();
            }
            else if (happy==1){
                translateToWord.setText("");
                /*translateToWord.setText("-변환 결과가 없습니다-");*/
                Toast.makeText(this, "변환 결과가 없습니다.", Toast.LENGTH_LONG).show();
            }
            else {
                translateToWord.setText(lastStr);
            }
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

