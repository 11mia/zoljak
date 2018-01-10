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

public class StudyConsonantActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2,frag3;
    Button button1;
    Button button2;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_consonant);
        setTitle("한글자음");
        manager = (FragmentManager)getFragmentManager();
        frag1 = new Fragment_Initial_sound();
        frag2 = new Fragment_Ssang();
        frag3 = new Fragment_Final_consonant();
        button1=(Button)findViewById(R.id.initial);
        button2=(Button)findViewById(R.id.finalconsonant);
        button3=(Button)findViewById(R.id.ssang);



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
