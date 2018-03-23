package com.example.miseon.braille;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class supplementLibraryActivity extends AppCompatActivity {
    SQLiteDatabase sqlitedb;
    DBManager dbmanager;
    LinearLayout layout;

    private ListView listview;
    private ListViewAdapter adapter;
    private String[] name=new String[39];
    private String[] address=new String[39];
    private String[] detail=new String[39];
    private String[] url = new String[39];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_library);
        setTitle("점자 도서관");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ListViewAdapter();
        listview = (ListView)findViewById(R.id.List_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        supplementLibraryMapActivity.class); // 다음넘어갈 화면
                ListVO item = (ListVO) adapter.getItem(position);
                String name = item.getname();
                String address = item.getaddress();
                String detail = item.getdetail();
                String url = item.geturl();
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                intent.putExtra("detail",detail);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });




        try {
            dbmanager = new DBManager(this);
            sqlitedb = dbmanager.getReadableDatabase();
            Cursor cursor;
            sqlitedb.beginTransaction();
            String str_name;
            String str_address;
            String address_detail;
            String url_detail;
            int i=-1;

            cursor = sqlitedb.query("Library",null,null,null,null,null,"num");
            while(cursor.moveToNext()){
                i++;

                str_name =cursor.getString(cursor.getColumnIndex("name"));
                str_address=cursor.getString(cursor.getColumnIndex("address"));
                address_detail=cursor.getString(cursor.getColumnIndex("detail"));
                url_detail=cursor.getString(cursor.getColumnIndex("url"));
                name[i]=str_name;
                address[i]=str_address;
                detail[i]=address_detail;
                url[i]=url_detail;
                adapter.addVO(name[i],address[i],detail[i],url[i]);

            }

            sqlitedb.setTransactionSuccessful();

        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqlitedb.endTransaction();
        }

        sqlitedb.close();


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
