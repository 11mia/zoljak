package com.example.miseon.braille;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DBManager extends SQLiteOpenHelper {
    ContentValues values = new ContentValues();
    public DBManager(Context context){
        super(context,"myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //db.execSQL("Drop table Braille;");
        db.execSQL("create table Braille(num INTEGER,letter text,braille text,type text);");
        try{
            int count=1;
            String[] letter1={"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
            String[] initial={"in1","in2","in3","in4","in5","in6","in7","in8","in9","in10","in11","in12","in13","in14"};
            String string="@string/";
            String pic;
            long newRowId;
            for(int i=1;i<=14;i++) {
                pic = string+initial[i-1];
                values.put("num", count);
                values.put("letter", letter1[i - 1]);
                values.put("braille", pic);
                values.put("type", "초성");
                db.insert("Braille",null,values);
            }





        }catch(SQLiteException e){

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
