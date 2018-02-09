package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by vdsym on 2018-01-31.
 */

public class StoryBookActivity  extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storybook);
        setTitle("점자 이야기");
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

    public void goToJustice(View v){
        Intent it = new Intent(this,StoryBookJusticeHistoryActivity.class);
        startActivity(it);
    }


    public void goToConstruction(View v){
        Intent it = new Intent(this,StoryBookConstructionActivity.class);
        startActivity(it);
    }

    public void goToInventor(View v) {
        Intent it = new Intent(this, StoryBookInventorActivity.class);
        startActivity(it);
    }
    public void goToTrait(View v) {
        Intent it = new Intent(this, StoryBookTraitActivity.class);
        startActivity(it);

    }


}
