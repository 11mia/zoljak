package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by vdsym on 2018-02-07.
 */


public class SupplementLifeActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_life);
        setTitle("실생활 점자");
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

   public void goToLife(View v) {
        int id = v.getId();
        ImageButton button=(ImageButton)v.findViewById(id);
        String tag=(String)button.getTag();


        Intent it = new Intent(this,SupplementLifeActivity2.class);
        it.putExtra("tag", tag);
        startActivity(it);

    }


}