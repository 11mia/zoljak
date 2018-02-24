package com.example.miseon.braille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class supplementLibraryMapActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        setTitle("점자 도서관 상세정보");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        String name = it.getStringExtra("name");
        String address = it.getStringExtra("address");
        Toast.makeText(this,"name : "+name+", address : "+address,Toast.LENGTH_LONG).show();

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
