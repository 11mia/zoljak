package com.example.miseon.braille;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by vdsym on 2018-02-07.
 */


public class SupplementLifeActivity2  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_life2);


        LinearLayout layout_life=(LinearLayout)findViewById(R.id.lifelayout);
        ImageButton button_life=(ImageButton)findViewById(R.id.lifeimage);
        TextView text_life=(TextView)findViewById(R.id.lifetext);

        Intent it= getIntent();
        String tag=it.getStringExtra("tag");

        Resources res = getResources();

        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics());
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);//단위로 dp를 사용하기 위함.
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(20,20,20,20);

        final int width1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics());
        final int height1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        final int width2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width1, height1);//단위로 dp를 사용하기 위함.
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(width2, height1);//단위로 dp를 사용하기 위함.

        layoutParams1.gravity = Gravity.CENTER;
        layoutParams2.gravity = Gravity.CENTER;
        layoutParams1.setMargins(10,20,10,20);
        layoutParams2.setMargins(10,0,10,20);


        if(tag.equals("1")) {
            setTitle("엘리베이터");
            text_life.setText("엘리베이터");
            ImageView image_life=new ImageView(this);
            image_life.setLayoutParams(layoutParams);
            int id_img = res.getIdentifier("elevator_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape1));

            int id_img2 = res.getIdentifier("shape_button1", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

            int id_img3=res.getIdentifier("elevator_image", "drawable", getPackageName());
            image_life.setImageResource(id_img3);
            layout_life.addView(image_life);


        }

        else if (tag.equals("2")) {
            setTitle("보행");
            text_life.setText("보행");
            ImageView image_life=new ImageView(this);
            image_life.setLayoutParams(layoutParams);
            int id_img = res.getIdentifier("street_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
           button_life.setBackground(this.getResources().getDrawable(R.drawable.shape3));

            int id_img2 = res.getIdentifier("shape_button2", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

            int id_img3=res.getIdentifier("street_image", "drawable", getPackageName());
            image_life.setImageResource(id_img3);
            layout_life.addView(image_life);

        }

        else if (tag.equals("3")) {
            setTitle("대중교통");
            text_life.setText("대중교통");

            int id_img2 = res.getIdentifier("shape_button3", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);
            int id_img = res.getIdentifier("sub2", "drawable", getPackageName());
            ImageView im2 = new ImageView(this);
            im2.setLayoutParams(layoutParams1);
            im2.setImageResource(id_img);
            layout_life.addView(im2);
            id_img = res.getIdentifier("sub1", "drawable", getPackageName());
            ImageView im3 = new ImageView(this);
            im3.setLayoutParams(layoutParams2);
            im3.setImageResource(id_img);
            layout_life.addView(im3);
            id_img = res.getIdentifier("subway_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape5));
        }

        else if (tag.equals("4")) {
            setTitle("기타");
            text_life.setText("기타");
            int id_img = res.getIdentifier("life_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape6));

            int id_img2 = res.getIdentifier("shape_button4", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

            int id_img3 = res.getIdentifier("bank1", "drawable", getPackageName());
            ImageView im2 = new ImageView(this);
            im2.setLayoutParams(layoutParams1);
            im2.setImageResource(id_img3);
            layout_life.addView(im2);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape6));

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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


}