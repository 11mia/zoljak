package com.example.miseon.braille;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class ManualActivity extends AppCompatActivity {
    int page;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        setTitle("도움말");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        page =it.getIntExtra("page",1);
        ImageView im = (ImageView)this.findViewById(R.id.manualIV);
        Resources res = getResources();
        String str = "p"+page;
        int id_img = res.getIdentifier(str, "drawable", getPackageName());
        im.setImageResource(id_img);

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
            finish();
        }
        else{
            Intent it = new Intent(this,ManualActivity.class);
            it.putExtra("page",--page);
            startActivity(it);
            finish();
        }

    }

    public void clickNext(View v){
        if(page==12){
            Toast.makeText(this,"마지막 페이지 입니다.",Toast.LENGTH_LONG).show();
            return;
        }else{
            Intent it = new Intent(this,ManualActivity.class);
            it.putExtra("page",++page);
            startActivity(it);
            finish();
        }

    }
}
