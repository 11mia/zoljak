package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by vdsym on 2018-02-01.
 */

public class SupplementActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement);
        setTitle("점자 부록");
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

    public void goToTrait(View v){


    }
    public void goToRule(View v){
        Intent it = new Intent(this,SupplementRuleActivity.class);
        startActivity(it);


    }
    public void goToRealLife(View v){
        Intent it = new Intent(this,SupplementLifeActivity.class);
        startActivity(it);

    }
    public void goToManual(View v){

    }

}
