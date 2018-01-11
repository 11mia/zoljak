package com.example.miseon.braille;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by vdsym on 2018-01-11.
 */

public class TranslateActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2;
    Button button1;
    Button button2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        setTitle("점자 번역");
        manager = (FragmentManager)getFragmentManager();


        frag1 = new Fragment_Translate_gultojeom();
        frag2 = new Fragment_Translate_jeomtogul();

        button1=(Button)findViewById(R.id.gultojeom);
        button2=(Button)findViewById(R.id.jeomtogul);
    }

    public void GoBack(View v){
        finish();
    }
    public void clicktranslate(View v) {

        int id = v.getId(); //클릭한 버튼입니다.
        Resources res = getResources();
        Drawable drawable1 = res.getDrawable(R.drawable.shape_button);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list);


        switch (id) {
            case R.id.gultojeom://button1
                button1.setBackground(drawable2);
                button1.setTextColor(Color.WHITE);
                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);

                tran = manager.beginTransaction();
                tran.replace(R.id.translateview, frag1);
                tran.commit();
                break;

            case R.id.jeomtogul://button2
                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);
                button2.setBackground(drawable2);
                button2.setTextColor(Color.WHITE);

                tran = manager.beginTransaction();
                tran.replace(R.id.translateview, frag2);
                tran.commit();
                break;
        }
    }
}