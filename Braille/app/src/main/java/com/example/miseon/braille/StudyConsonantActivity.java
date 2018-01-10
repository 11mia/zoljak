package com.example.miseon.braille;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudyConsonantActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2,frag3;

    Button button_before = null;
    int id_before=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_consonant);
        setTitle("한글자음");
        manager = (FragmentManager)getFragmentManager();
        frag1 = new Fragment_Initial_sound();
        frag2 = new Fragment_Ssang();
        frag3 = new Fragment_Final_consonant();


    }
    public void GoBack(View v){
        finish();
    }

    public void consonantClick(View v){
        switch(v.getId()){
            case R.id.initial:
                tran = manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag1);
                tran.commit();
                break;
            case R.id.ssang:
                tran=manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag2);
                tran.commit();
                break;
            case R.id.finalconsonant:
                tran=manager.beginTransaction();
                tran.replace(R.id.consonantFragment,frag3);
                tran.commit();
                break;

        }

    }
    public void click(View v){
      /*
        int id = v.getId();
        Button button = (Button)findViewById(id);
        Resources res = getResources();
        if(id_before!=-1){
            button_before=(Button)findViewById(id_before);
            Drawable drawable = res.getDrawable(R.drawable.shape_button);
            button_before.setBackground(drawable);
        }
        if(id!=id_before){
            Drawable drawable=res.getDrawable(R.drawable.shape_list);
            button.setBackground(drawable);
            id_before=id;
        }
        */

    }
}
