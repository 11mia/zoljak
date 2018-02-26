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


public class StoryBookInventorActivity  extends AppCompatActivity {
    Button button1;
    Button button2;
    FragmentManager manager;
    FragmentTransaction tran;
    Fragment frag1,frag2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storybook_braille_history);
        setTitle("점자 역사");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1=(Button)findViewById(R.id.history1);
        button2=(Button)findViewById(R.id.history2);
        manager = (FragmentManager)getFragmentManager();
        frag1 = new Fragment_History();
        frag2 = new Fragment_korea_braille();



    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void justiceHistoryClick(View v){

        int id = v.getId(); //클릭한 버튼
        Resources res = getResources();
        Drawable drawable1=res.getDrawable(R.drawable.shape_button1);
        Drawable drawable2 = res.getDrawable(R.drawable.shape_list1);


        switch(id){
            case R.id.history1://button1
                setTitle("점자 창시");
                button1.setBackground(drawable2);
                button1.setTextColor(Color.WHITE);
                button2.setBackground(drawable1);
                button2.setTextColor(Color.BLACK);

                tran = manager.beginTransaction();
                tran.replace(R.id.change,frag1);
                tran.commit();
                break;
            case R.id.history2://button2
                setTitle("훈맹정음");
                button1.setBackground(drawable1);
                button1.setTextColor(Color.BLACK);

                button2.setBackground(drawable2);
                button2.setTextColor(Color.WHITE);

                tran=manager.beginTransaction();
                tran.replace(R.id.change,frag2);
                tran.commit();
                break;


        }

    }

}
