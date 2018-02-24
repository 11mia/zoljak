package com.example.miseon.braille;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        ImageView image_life=(ImageView)findViewById(R.id.lifeimage2);

        Intent it= getIntent();
        String tag=it.getStringExtra("tag");

        Resources res = getResources();

        ActionBar ab = getSupportActionBar();

        if(tag.equals("1")) {
/*
            ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A1C1C1")));
*/
            setTitle("엘리베이터");
             text_life.setText("엘리베이터");
            int id_img = res.getIdentifier("elevator_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape1));

            int id_img2 = res.getIdentifier("shape_button1", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

            int id_img3=res.getIdentifier("elevator_image", "drawable", getPackageName());
            image_life.setImageResource(id_img3);


        }

        else if (tag.equals("2")) {
/*
            ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AEB7CC")));
*/
            setTitle("보행");
            text_life.setText("보행");
            int id_img = res.getIdentifier("street_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
           button_life.setBackground(this.getResources().getDrawable(R.drawable.shape3));

            int id_img2 = res.getIdentifier("shape_button2", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

            int id_img3=res.getIdentifier("street_image", "drawable", getPackageName());
            image_life.setImageResource(id_img3);
        }

        else if (tag.equals("3")) {
/*
            ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8D82AF")));
*/
            setTitle("버스 정류장");
            text_life.setText("버스 정류장");
            int id_img = res.getIdentifier("bus_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape5));

            int id_img2 = res.getIdentifier("shape_button3", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);
        }

        else if (tag.equals("4")) {
/*
            ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D6B676")));
*/
            setTitle("지하철");
            text_life.setText("지하철");
            int id_img = res.getIdentifier("subway_icon", "drawable", getPackageName());
            button_life.setImageResource(id_img);
            button_life.setBackground(this.getResources().getDrawable(R.drawable.shape6));

            int id_img2 = res.getIdentifier("shape_button4", "drawable", getPackageName());
            layout_life.setBackgroundResource(id_img2);

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

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



}