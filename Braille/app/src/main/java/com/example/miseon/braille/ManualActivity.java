package com.example.miseon.braille;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManualActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    int page;
    ImageView im;
    private ConstraintLayout main;
    private static final int SWIPE_MIN_DISTANCE = 90;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureScanner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        page =it.getIntExtra("page",1);
        im = (ImageView)this.findViewById(R.id.manualIV);
        Resources res = getResources();
        String str = "p"+page;
        int id_img = res.getIdentifier(str, "drawable", getPackageName());
        im.setImageResource(id_img);
        setTitle("도움말("+page+"/12)");


        gestureScanner = new GestureDetector(this);
        main = (ConstraintLayout)findViewById(R.id.manualLayout);

    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }

    public boolean onDown(MotionEvent e) {
        return true;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;

            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if(page==12){
                    Toast toast = Toast.makeText(this,"마지막 페이지 입니다.",Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                    toast.show();
                }else{
                    Intent it = new Intent(this,ManualActivity.class);
                    it.putExtra("page",++page);
                    startActivity(it);
                    im.setImageResource(0);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                    finish();
                }
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if(page==1){
                    im.setImageResource(0);
                    finish();
                }
                else{
                    Intent it = new Intent(this,ManualActivity.class);
                    it.putExtra("page",--page);
                    startActivity(it);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                    im.setImageResource(0);
                    finish();
                }

            }

        } catch (Exception e) {

        }
        return true;
    }

    public void onLongPress(MotionEvent e) {

    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return true;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {

        return true;
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

    public void clickPrevious(View v){
        if(page==1){
            im.setImageResource(0);
            finish();
        }
        else{
            Intent it = new Intent(this,ManualActivity.class);
            it.putExtra("page",--page);
            startActivity(it);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            im.setImageResource(0);
            finish();
        }

    }

    public void clickNext(View v){
        if(page==12){
            Toast toast = Toast.makeText(this,"마지막 페이지 입니다.",Toast.LENGTH_LONG);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            toast.show();
            return;
        }else{
            Intent it = new Intent(this,ManualActivity.class);
            it.putExtra("page",++page);
            startActivity(it);
            im.setImageResource(0);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            finish();
        }

    }
}
