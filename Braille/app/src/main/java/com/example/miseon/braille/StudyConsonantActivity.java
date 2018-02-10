package com.example.miseon.braille;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class StudyConsonantActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2,frag3;
    Button button1;
    Button button2;
    Button button3;

    LinearLayout layout;

    Resources res;
    TextView tv1;
    TextView tv2;
    LinearLayout v1;
    LinearLayout v2;
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    String letter;
    int dot_num;
    final Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_consonant1);
        setTitle("한글자음");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        manager = (FragmentManager)getFragmentManager();
        frag1 = new Fragment_Initial_sound();
        frag2 = new Fragment_Ssang();
        frag3 = new Fragment_Final_consonant();

        button1=(Button)findViewById(R.id.initial);
        button2=(Button)findViewById(R.id.finalconsonant);
        button3=(Button)findViewById(R.id.ssang);

        res = getResources();


    }
    public void GoBack(View v){
        finish();
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


    public void consonantClick(View v){

        int id = v.getId(); //클릭한 버튼
        Resources res = getResources();
        Drawable drawable1=res.getDrawable(R.drawable.shape_button1);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list1);


        switch(id){
            case R.id.initial://button1
                button1.setBackground(drawable2);
                button1.setTextColor(Color.WHITE);
                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);

                button3.setBackground(drawable1);
                button3.setTextColor(Color.BLACK);

                tran = manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag1);
                tran.commit();
                break;
            case R.id.ssang://button3
                button3.setBackground(drawable2);
                button3.setTextColor(Color.WHITE);

                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);

                tran=manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag2);
                tran.commit();
                break;
            case R.id.finalconsonant://button2
                button2.setBackground(drawable2);
                button2.setTextColor(Color.WHITE);

                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button3.setBackground(drawable1);
                button3.setTextColor(Color.BLACK);

                tran=manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag3);
                tran.commit();
                break;

        }

    }
    public void click(View v) {
    }


    public void clickConsonant(int num1,int num2){
       // Toast.makeText(this,"num1: "+num1+", num2: "+num2,Toast.LENGTH_SHORT).show();
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
                                    v1.removeAllViews();
                                    v2.removeAllViews();
                                }
                            });

            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_study, null);

            tv1 = (TextView)layout.findViewById(R.id.wordTv1);
            tv2 = (TextView)layout.findViewById(R.id.wordTv2);
            v1 = (LinearLayout)layout.findViewById(R.id.wordView1);
            v2 = (LinearLayout)layout.findViewById(R.id.wordView2);

            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+num1},null,null,"num");
            if(cursor.moveToNext()){
                letter = cursor.getString(cursor.getColumnIndex("letter"));
                dot_num = cursor.getInt(cursor.getColumnIndex("dot_num"));

                //Toast.makeText(this,letter,Toast.LENGTH_SHORT).show();
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

            cursor = sqlitedb.query("Braille",null,"num=?",new String[]{""+num2},null,null,"num");
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
                //Toast.makeText(this,letter,Toast.LENGTH_SHORT).show();

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

