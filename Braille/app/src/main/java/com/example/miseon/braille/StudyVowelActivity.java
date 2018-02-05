package com.example.miseon.braille;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by miseon on 2018-01-15.
 */

public class StudyVowelActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    LinearLayout v1;
    LinearLayout v2;
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    String letter;
    int dot_num;
    String dot_1;
    String dot_2;
    String dot_3;
    String dot_4;
    final Context context = this;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_vowel);
        setTitle("한글모음");
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


    public void clickVowel(View v){
        int id = v.getId();
        Resources res = getResources();
        LinearLayout clickedLayout = (LinearLayout)findViewById(id);
        String tag = (String)clickedLayout.getTag();

        int imNum1 = Integer.parseInt(tag);
        int imNum2 = imNum1++;
        Cursor cursor;
        int id_img;

        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            sqlitedb.beginTransaction();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            alertDialogBuilder.setTitle("예시 단어")
                    .setCancelable(false)
                    .setPositiveButton("닫기",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                }
                            });

            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_study, null);

            tv1 = (TextView)layout.findViewById(R.id.wordTv1);
            tv2 = (TextView)layout.findViewById(R.id.wordTv2);
            v1 = (LinearLayout)layout.findViewById(R.id.wordView1);
            v2 = (LinearLayout)layout.findViewById(R.id.wordView2);

            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+imNum1},null,null,"num");
            if(cursor.moveToNext()){
                letter = cursor.getString(cursor.getColumnIndex("letter"));
                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));

                Toast.makeText(this,letter,Toast.LENGTH_SHORT).show();
                tv1.setText(letter);

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
                    v1.addView(iv);
                }

            }

            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+imNum2},null,null,"num");
            if(cursor.moveToNext()){
                letter = cursor.getString(cursor.getColumnIndex("letter"));
                tv2.setText(letter);
                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));
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
                    v2.addView(iv);
                }
                Toast.makeText(this,letter,Toast.LENGTH_SHORT).show();

            }

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

        sqlitedb.close();
    }

}
