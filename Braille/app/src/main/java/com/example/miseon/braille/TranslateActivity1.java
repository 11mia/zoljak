package com.example.miseon.braille;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslateActivity1 extends AppCompatActivity {
    EditText editText;
    TextView textView;
   // TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    LinearLayout imageView;

    String chos="";
    String juns="";
    String jongs="";
    String arr="";

    int count;

    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    int dot_num;

    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate1);
        setTitle("변환(한글->점자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editText = (EditText) this.findViewById(R.id.inputtext);
        textView = (TextView) this.findViewById(R.id.outputtext);
     //   textView1 = (TextView) this.findViewById(R.id.outputtextdivide);
        textView2=(TextView) this.findViewById(R.id.outputimagecho);
        textView3=(TextView) this.findViewById(R.id.outputimagejun);
        textView4=(TextView) this.findViewById(R.id.outputimagejong);
        imageView = (LinearLayout) this.findViewById(R.id.imagelayout);

/*
        List<String> jasoList = new ArrayList<String>();

        try {
            jasoList = HangulParser.disassemble('한');
        } catch (HangulParserException e) {
            e.printStackTrace();
        }

*/
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

    void goToTranslateToJeom(View v) throws IOException {

        chos = "";
        juns = "";
        jongs = "";
        arr = "";

        char[] chos_2 = new char[20];
        char[] juns_2 = new char[20];
        char[] jong_2 = new char[20];
        count = 0;

        String al1 = editText.getText().toString();

        imageView.removeAllViews();
        textView.setText("'" + al1 + "'" + "의 점역결과");


        // ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ
        final char[] CHO =
                {0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141,
                        0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
                        0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};

        //ㅏㅐㅑㅒㅓㅔㅕㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
        final char[] JUN =
                {0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155,
                        0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b,
                        0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161,
                        0x3162, 0x3163};

        // ㄱㄲㄳㄴㄵㄶㄷㄹㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ
        final char[] JON =
                {0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136,
                        0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d,
                        0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144,
                        0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b,
                        0x314c, 0x314d, 0x314e};


        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

        arr = "";

        for (int k = 0; k < al1.length(); k++) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            char test = al1.charAt(k);

            if (test >= 0xAC00) {
                char uniVal = (char) (test - 0xAC00);

                char cho = (char) (((uniVal - (uniVal % 28)) / 28) / 21);
                char jun = (char) (((uniVal - (uniVal % 28)) / 28) % 21);
                char jon = (char) (uniVal % 28);


                System.out.println();
                System.out.println("" + test + "// 0x" + Integer.toHexString((char) test));

                System.out.println("" + CHO[cho] + "// 0x"
                        + Integer.toHexString((char) cho));

                arr += CHO[cho] + "";
                chos += CHO[cho];
                chos_2[count] = CHO[cho];

                System.out.println("" + JUN[jun] + "// 0x"
                        + Integer.toHexString((char) jun));

                arr += JUN[jun] + "";
                juns += JUN[jun];
                juns_2[count] = JUN[jun];


                if ((char) jon != 0x0000) {
                    System.out.println("" + JON[jon] + "// 0x"
                            + Integer.toHexString((char) jon));
                    arr += JON[jon] + "";
                    jongs += JON[jon];
                    jong_2[count] = JON[jon];


                } else {
                    jongs += 'A';
                    jong_2[count] = 'A';
                }


            } else {//스페이스바처리
                arr += "  ";
                chos += 'B';
                juns += 'B'; //중성
                jongs += 'B';


                chos_2[count] = 'B';
                juns_2[count] = 'B';
                jong_2[count] = 'B';


            }
            count++;

        }
        Cursor cursor=null;
        Cursor cursor2 = null;
        Cursor cursor4 = null;
        Cursor cursor5 = null;



        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();

            int id_img;
            Resources res = getResources();

            for (int start = 0; start < count; start++) {

                if(chos_2[start]=='B') {//스페이스바 처리 부분
                    ImageView iv = new ImageView(this); //추가할 이미지뷰

                    iv.setImageResource(R.drawable.backgroundcolor);
                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17, getResources().getDisplayMetrics());//30dp
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                    layoutParams.gravity = Gravity.CENTER;
                    iv.setLayoutParams(layoutParams);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setGravity(Gravity.CENTER);
                    imageView.addView(iv);
                }

                else {//스페이스바 아닌 부분!


                    if((chos_2[start]=='ㄲ'||chos_2[start]=='ㅆ'||chos_2[start]=='ㅉ')&&juns_2[start]=='ㅏ'){//case1

                            if(chos_2[start]=='ㄲ'){
                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                iv.setImageResource(R.drawable.c777776);//된소리 777776표현해주기
                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);


                                ImageView iv1 = new ImageView(this);
                                iv1.setImageResource(R.drawable.c127476);
                                layoutParams.gravity = Gravity.CENTER;//까 or 싸
                                iv1.setLayoutParams(layoutParams);
                                iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv1);
                            }
                            else if (chos_2[start]=='ㅆ'){
                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                iv.setImageResource(R.drawable.c777776);//된소리 777776표현해주기
                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);


                                ImageView iv1 = new ImageView(this);
                                iv1.setImageResource(R.drawable.c123777);
                                layoutParams.gravity = Gravity.CENTER;//까 or 싸
                                iv1.setLayoutParams(layoutParams);
                                iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv1);
                            }
                            else if (chos_2[start]=='ㅉ') {
                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                iv.setImageResource(R.drawable.c777776);//된소리 777776표현해주기
                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);


                                ImageView iv1 = new ImageView(this);
                                iv1.setImageResource(R.drawable.c777476);
                                layoutParams.gravity = Gravity.CENTER;//까 or 싸
                                iv1.setLayoutParams(layoutParams);
                                iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv1);



                            }

                            if(jong_2[start]!='A'){
                                cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(jong_2[start]), "변환종성"}, null, null, "letter");


                                if(cursor.moveToNext()){

                                    dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
                                    for (int i = 1; i <= dot_num; i++) {

                                        ImageView iv1 = new ImageView(this); //추가할 이미지뷰
                                        String str = cursor.getString(cursor.getColumnIndex("dot_" + i));
                                        id_img = res.getIdentifier(str, "drawable", getPackageName());
                                        iv1.setImageResource(id_img);
                                        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                       final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                        layoutParams.gravity = Gravity.CENTER;
                                        iv1.setLayoutParams(layoutParams);
                                        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setGravity(Gravity.CENTER);
                                        imageView.addView(iv1);
                                    }

                                }

                            }//까 or 싸에 종성이 있으면

                    }

                    else if((chos_2[start]=='ㄱ'||chos_2[start]=='ㄲ')&&juns_2[start]=='ㅓ'&&jong_2[start]=='ㅅ'){//case2
                        if(chos_2[start]=='ㄲ') {//껏의 된소리 앞에 표현해주기
                           ImageView iv = new ImageView(this);
                           iv.setImageResource(R.drawable.c777776);

                            final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                            final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                            layoutParams.gravity = Gravity.CENTER;
                            iv.setLayoutParams(layoutParams);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setGravity(Gravity.CENTER);
                            imageView.addView(iv);

                        }


                        ImageView iv = new ImageView(this);
                        iv.setImageResource(R.drawable.c777456);//것의 dot_1 부분

                        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                        layoutParams.gravity = Gravity.CENTER;
                        iv.setLayoutParams(layoutParams);
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setGravity(Gravity.CENTER);
                        imageView.addView(iv);



                        ImageView iv1 = new ImageView(this);
                        iv1.setImageResource(R.drawable.c723477);//'것'의 dot_2부분
                        layoutParams.gravity = Gravity.CENTER;
                        iv1.setLayoutParams(layoutParams);
                        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setGravity(Gravity.CENTER);
                        imageView.addView(iv1);


                    }





                    else  if ((juns_2[start] == 'ㅏ') && (chos_2[start] == 'ㄱ' || chos_2[start] == 'ㄴ' || chos_2[start] == 'ㄷ' || chos_2[start] == 'ㅁ'//case3
                            || chos_2[start] == 'ㅂ' || chos_2[start] == 'ㅅ' || chos_2[start] == 'ㅈ' || chos_2[start] == 'ㅋ' || chos_2[start] == 'ㅌ' || chos_2[start] == 'ㅍ' || chos_2[start] == 'ㅎ')) {
                        if (chos_2[start] == 'ㄱ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"가", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㄴ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"나", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㄷ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"다", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅁ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"마", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅂ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"바", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅅ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"사", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅈ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"자", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅋ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"카", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅌ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"타", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅍ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"파", "한글약어"}, null, null, "letter");
                        } else if (chos_2[start] == 'ㅎ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"하", "한글약어"}, null, null, "letter");
                        }

                        if (cursor.moveToNext()) {

                            dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
                            for (int i = 1; i <= dot_num; i++) {

                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                String str = cursor.getString(cursor.getColumnIndex("dot_" + i));
                                id_img = res.getIdentifier(str, "drawable", getPackageName());
                                iv.setImageResource(id_img);
                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);
                            }

                        }

                        if (jong_2[start] == 'A') {
                        } else {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(jong_2[start]), "변환종성"}, null, null, "letter");


                            if (cursor.moveToNext()) {

                                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
                                for (int i = 1; i <= dot_num; i++) {

                                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                                    String str = cursor.getString(cursor.getColumnIndex("dot_" + i));
                                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                                    iv.setImageResource(id_img);
                                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                    layoutParams.gravity = Gravity.CENTER;
                                    iv.setLayoutParams(layoutParams);
                                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                    imageView.setGravity(Gravity.CENTER);
                                    imageView.addView(iv);
                                }

                            }//강 낭 당 이런거 관련된거


                        }










                    } else if ((juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄱ')//case4
                            || (juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄴ')
                            || (juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄹ')
                            || (juns_2[start] == 'ㅕ' && jong_2[start] == 'ㄴ')
                            || (juns_2[start] == 'ㅕ' && jong_2[start] == 'ㄹ')
                            || (juns_2[start] == 'ㅕ' && jong_2[start] == 'ㅇ')
                            || (juns_2[start] == 'ㅗ' && jong_2[start] == 'ㄱ')
                            || (juns_2[start] == 'ㅗ' && jong_2[start] == 'ㄴ')
                            || (juns_2[start] == 'ㅗ' && jong_2[start] == 'ㅇ')
                            || (juns_2[start] == 'ㅜ' && jong_2[start] == 'ㄴ')
                            || (juns_2[start] == 'ㅜ' && jong_2[start] == 'ㄹ')
                            || (juns_2[start] == 'ㅡ' && jong_2[start] == 'ㄴ')
                            || (juns_2[start] == 'ㅡ' && jong_2[start] == 'ㄹ')
                            || (juns_2[start] == 'ㅣ' && jong_2[start] == 'ㄴ')
                            ) {


                        if (chos_2[start] != 'ㅇ') {
                            cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(chos_2[start]), "변환초성"}, null, null, "letter");


                            if (cursor.moveToNext()) {
                                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
                                for (int i = 1; i <= dot_num; i++) {

                                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                                    String str = cursor.getString(cursor.getColumnIndex("dot_" + i));
                                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                                    iv.setImageResource(id_img);


                                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                    layoutParams.gravity = Gravity.CENTER;
                                    iv.setLayoutParams(layoutParams);
                                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                    imageView.setGravity(Gravity.CENTER);
                                    imageView.addView(iv);
                                }

                            }

                        }


                        if (juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄱ') {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"억", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"언", "한글약어"}, null, null, "letter");

                        } else if ((juns_2[start] == 'ㅓ' && jong_2[start] == 'ㄹ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"얼", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅕ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"연", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅕ' && jong_2[start] == 'ㄹ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"열", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅕ' && jong_2[start] == 'ㅇ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"영", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅗ' && jong_2[start] == 'ㄱ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"옥", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅗ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"온", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅗ' && jong_2[start] == 'ㅇ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"옹", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅜ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"운", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅜ' && jong_2[start] == 'ㄹ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"울", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅡ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"은", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅡ' && jong_2[start] == 'ㄹ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"을", "한글약어"}, null, null, "letter");
                        } else if ((juns_2[start] == 'ㅣ' && jong_2[start] == 'ㄴ')) {
                            cursor2 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{"인", "한글약어"}, null, null, "letter");
                        } else {
                        }
                        ;


                        if (cursor2.moveToNext()) {
                            dot_num = cursor2.getInt(cursor2.getColumnIndex("dot_num"));
                            for (int i = 1; i <= dot_num; i++) {

                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                String str = cursor2.getString(cursor2.getColumnIndex("dot_" + i));
                                id_img = res.getIdentifier(str, "drawable", getPackageName());
                                iv.setImageResource(id_img);


                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);
                            }


                        }

                    } else {//case5 보통의 캐릭터 하나 처리부분~

                        Cursor cursorlast = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(chos_2[start]), "변환초성"}, null, null, "letter");


                        if (chos_2[start] != 'ㅇ') {
                            if (cursorlast.moveToNext()) {
                                dot_num = cursorlast.getInt(cursorlast.getColumnIndex("dot_num"));
                                for (int i = 1; i <= dot_num; i++) {

                                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                                    String str = cursorlast.getString(cursorlast.getColumnIndex("dot_" + i));
                                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                                    iv.setImageResource(id_img);


                                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                    layoutParams.gravity = Gravity.CENTER;
                                    iv.setLayoutParams(layoutParams);
                                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                    imageView.setGravity(Gravity.CENTER);
                                    imageView.addView(iv);

                                }
                            }

                        }

                        Cursor cursor10 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(juns_2[start]), "중성"}, null, null, "letter");

                        if (cursor10.moveToNext()) {
                            dot_num = cursor10.getInt(cursor10.getColumnIndex("dot_num"));
                            for (int i = 1; i <= dot_num; i++) {

                                ImageView iv = new ImageView(this); //추가할 이미지뷰
                                String str = cursor10.getString(cursor10.getColumnIndex("dot_" + i));
                                id_img = res.getIdentifier(str, "drawable", getPackageName());
                                iv.setImageResource(id_img);


                                final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                layoutParams.gravity = Gravity.CENTER;
                                iv.setLayoutParams(layoutParams);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageView.setGravity(Gravity.CENTER);
                                imageView.addView(iv);

                            }
                        }


                        if (jong_2[start] != 'A') {
                            Cursor cursor11 = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(jong_2[start]), "변환종성"}, null, null, "letter");

                            if (cursor11.moveToNext()) {
                                dot_num = cursor11.getInt(cursor11.getColumnIndex("dot_num"));
                                for (int i = 1; i <= dot_num; i++) {

                                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                                    String str = cursor11.getString(cursor11.getColumnIndex("dot_" + i));
                                    id_img = res.getIdentifier(str, "drawable", getPackageName());
                                    iv.setImageResource(id_img);


                                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                                    layoutParams.gravity = Gravity.CENTER;
                                    iv.setLayoutParams(layoutParams);
                                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                    imageView.setGravity(Gravity.CENTER);
                                    imageView.addView(iv);
                                }

                            }//if

                        }

                    }//else

                }//else 스페이스 안한거 처리
            }//for문
            sqlitedb.setTransactionSuccessful();


        }catch(SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();

         //   textView1.setText(arr);
            textView2.setText(chos);
            textView3.setText(juns);
            textView4.setText(jongs);
        }
    }

    void goToReset(View v) {
        editText.setText("");
        textView.setText("");
     //   textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
        imageView.removeAllViews();
    }
}
