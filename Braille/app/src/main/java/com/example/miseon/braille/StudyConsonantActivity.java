package com.example.miseon.braille;


/*
public class StudyConsonantActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2,frag3;
    Button button1;
    Button button2;
    Button button3;

    String inputtype;
    LinearLayout layout;
    DBManager dbmanager;
    SQLiteDatabase sqlitedb;
    Resources res;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_consonant1);
        setTitle("한글자음");

        button1=(Button)findViewById(R.id.initial);
        button2=(Button)findViewById(R.id.finalconsonant);
        button3=(Button)findViewById(R.id.ssang);
        dbmanager=new DBManager(this);
        sqlitedb = dbmanager.getReadableDatabase();
        layout = (LinearLayout)findViewById(R.id.consonantList);
        res = getResources();




    }
    public void GoBack(View v){
        finish();
    }

    public void consonantClick(View v){

        int id = v.getId(); //클릭한 버튼
        Resources res = getResources();
        Drawable drawable1=res.getDrawable(R.drawable.shape_button);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list);


        switch(id){
            case R.id.initial://button1
                button1.setBackground(drawable2);
                button1.setTextColor(Color.WHITE);
                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);

                button3.setBackground(drawable1);
                button3.setTextColor(Color.BLACK);
                inputtype = "초성";

                break;
            case R.id.ssang://button3
                button3.setBackground(drawable2);
                button3.setTextColor(Color.WHITE);

                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);
                inputtype = "쌍자음";

                break;
            case R.id.finalconsonant://button2
                button2.setBackground(drawable2);
                button2.setTextColor(Color.WHITE);

                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button3.setBackground(drawable1);
                button3.setTextColor(Color.BLACK);
                inputtype="종성";

                break;
        }

        //db찾아서 채우기
        try{
            Cursor cursor = sqlitedb.query("Braille",null,"type=?",new String[]{inputtype},null,null,"num");
            int i=1;
            int count=1;

            while(cursor.moveToNext()){
                String letter = cursor.getString(cursor.getColumnIndex("letter"));
                String braille=cursor.getString(cursor.getColumnIndex("braille"));
                Toast.makeText(this,"letter="+letter+"braille="+braille,Toast.LENGTH_LONG).show();


                LinearLayout layout_list = new LinearLayout(this);
                layout_list.setOrientation(LinearLayout.HORIZONTAL);
                layout_list.setId(i);

              //  if(count<=4){
                    LinearLayout content_layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout_list.addView(content_layout);
               // }
                TextView tv = new TextView(this);
                tv.setText(letter);
                layout_list.addView(tv);

                ImageView iv = new ImageView(this);
                int stringId = res.getIdentifier("in"+i,"string",getPackageName());
                String mykey = res.getString(stringId);
                int id_image = res.getIdentifier(mykey,"drawable",getPackageName());
                iv.setImageResource(id_image);
                layout_list.addView(iv);

            }



        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }



    }
    public void click(View v) {
    }
}
*/


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class StudyConsonantActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2,frag3;
    Button button1;
    Button button2;
    Button button3;

    String inputtype;
    LinearLayout layout;
    DBManager dbmanager;
    SQLiteDatabase sqlitedb;
    Resources res;




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
        Drawable drawable1=res.getDrawable(R.drawable.shape_button);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list);


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
}

