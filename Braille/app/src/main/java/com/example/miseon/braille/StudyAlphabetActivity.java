package com.example.miseon.braille;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by miseon on 2018-01-15.
 */

public class StudyAlphabetActivity extends AppCompatActivity{
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2;
    Button button1;
    Button button2;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_alphabet);
        setTitle("알파벳");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = (FragmentManager)getFragmentManager();
        frag1 = new Fragment_capital();
        frag2 = new Fragment_lower();
        button1=(Button)findViewById(R.id.capital);
        button2=(Button)findViewById(R.id.lower);
        res = getResources();


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


    public void alphabet(View v){

        int id = v.getId(); //클릭한 버튼
        Resources res = getResources();
        Drawable drawable1=res.getDrawable(R.drawable.shape_button);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list);


        switch(id){
            case R.id.capital://button1
                button1.setBackground(drawable2);
                button1.setTextColor(Color.WHITE);
                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);



                tran = manager.beginTransaction();
                tran.replace(R.id.alphabetFragment,frag1);
                tran.commit();
                break;
            case R.id.lower://button2

                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button2.setBackground(drawable2);
                button2.setTextColor(Color.WHITE);

                tran=manager.beginTransaction();
                tran.replace(R.id.alphabetFragment,frag2);
                tran.commit();
                break;

        }

    }

}
