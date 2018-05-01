package com.example.miseon.braille;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class TranslateActivity3 extends AppCompatActivity {


    EditText editText;
    TextView textView;
    LinearLayout imageView;
    // char[]temp;
    int count;

    SQLiteDatabase sqlitedb;
    DBManager dbmanager;

    int dot_num;
    String al1;
    Button result;
    Button reset;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate3);
        setTitle("변환(글자->점자)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //키보드 올리기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);



        editText = (EditText) this.findViewById(R.id.inputtext);
        textView = (TextView) this.findViewById(R.id.outputtext);
        imageView = (LinearLayout) this.findViewById(R.id.imagelayout);
        textView.setText("");


        editText.setPrivateImeOptions("defaultInputmode=english;"); //기본키패드를 영어로 설정
        editText.setImeOptions(EditorInfo.IME_NULL); //키보드에서 엔터 터치시 '완료'의미

        result = (Button)findViewById(R.id.resultbutton);
        result.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    goToTranslateToJeom(v);
                } catch (IOException e) {
                }
            }
        }));
        reset = (Button)findViewById(R.id.resetbutton);
        reset.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                goToReset(v);
            }
        }));

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
                InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                finish();
                return true;
            case R.id.translateHangul:
                it=new Intent(this,TranslateActivity1.class);
                startActivity(it);
                finish();
                break;
            case R.id.translateEnglish:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    void goToTranslateToJeom(View v) throws IOException {

        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        final EditText et = (EditText)findViewById(R.id.inputtext);
        imm.hideSoftInputFromWindow(et.getWindowToken(),0);//변환 클릭하면 키보드 쫙 사라진다
        al1="";
        int happy=0;

        al1 = editText.getText().toString();


        if (al1.length()>20) {
            Toast toast = Toast.makeText(this, "20자 이내로 입력하세요.", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            toast.show();
            editText.setText("");
            textView.setText("");
            imageView.removeAllViews();
            return;
        }

        imageView.removeAllViews();
        textView.setText("");
        count=0;

        char[] temp = new char[20];


        for(int i=0; i < al1.length();i++) {

            temp[i] = al1.charAt(i);
            count++;
        }


        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();

            int id_img;
            Resources res = getResources();
            int start;
            Cursor cursor;
            for ( start = 0; start < count; start++) {
                if(temp[start]=='A'||temp[start]=='B'||temp[start]=='C'||temp[start]=='D'||temp[start]=='E'||temp[start]=='F'||temp[start]=='G'||
                        temp[start]=='H'||temp[start]=='I'||temp[start]=='J'||temp[start]=='K'||temp[start]=='L'||temp[start]=='M'||temp[start]=='N'||
                        temp[start]=='O'||temp[start]=='P'||temp[start]=='Q'||temp[start]=='R'||temp[start]=='S'||temp[start]=='T'||temp[start]=='U'||
                        temp[start]=='V'||temp[start]=='W'||temp[start]=='X'||temp[start]=='Y'||temp[start]=='Z') {
                    cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(temp[start]), "대문자"}, null, null, "letter");
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
                else if (temp[start]=='a'||temp[start]=='b'||temp[start]=='c'||temp[start]=='d'||temp[start]=='e'||temp[start]=='f'||temp[start]=='g'||
                        temp[start]=='h'||temp[start]=='i'||temp[start]=='j'||temp[start]=='k'||temp[start]=='l'||temp[start]=='m'||temp[start]=='n'||
                        temp[start]=='o'||temp[start]=='p'||temp[start]=='q'||temp[start]=='r'||temp[start]=='s'||temp[start]=='t'||temp[start]=='u'||
                        temp[start]=='v'||temp[start]=='w'||temp[start]=='x'||temp[start]=='y'||temp[start]=='z') {


                    cursor = sqlitedb.query("Braille", null, "letter=?AND type=?", new String[]{String.valueOf(temp[start]), "소문자"}, null, null, "letter");
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
                else if( temp[start]=='.'||temp[start]==','||temp[start]=='?'||temp[start]=='!') {

                    ImageView iv = new ImageView(this); //추가할 이미지뷰
                    if (temp[start]=='.'){
                        iv.setImageResource(R.drawable.c727756);
                    } else if (temp[start] == ',') {
                        iv.setImageResource(R.drawable.c777757);
                    }
                    else if (temp[start] == '?') {
                        iv.setImageResource(R.drawable.c723776);
                    }
                    else if (temp[start] == '!') {
                        iv.setImageResource(R.drawable.c723757);
                    }

                    final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getResources().getDisplayMetrics());//30dp
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());//50dp
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.

                    layoutParams.gravity = Gravity.CENTER;
                    iv.setLayoutParams(layoutParams);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setGravity(Gravity.CENTER);
                    imageView.addView(iv);
                }


                else if (temp[start]==' ') {
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
                else {happy=1; break;}


            }//for문
            sqlitedb.setTransactionSuccessful();

        }catch(Exception e){
           // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
            sqlitedb.endTransaction();

            if (happy==1) {//There is no result
                imageView.removeAllViews();
                textView.setText("");
                Toast toast = Toast.makeText(this, "No Result", Toast.LENGTH_LONG);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                toast.show();

            }
            else
                textView.setText("Result of '" + al1 + "'");
        }
    }


    void goToReset(View v) {
        editText.setText("");//영어 입력받는 곳 리셋
        textView.setText("");//~의 점역 결과 리셋
        imageView.removeAllViews();//점자 이미지 보여주는 거 리셋


        //키보드 올리기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
