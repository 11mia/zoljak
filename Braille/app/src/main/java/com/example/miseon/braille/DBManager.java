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
        db.execSQL("create table Braille(num INTEGER,letter text,type text,dot_num INTEGER,dot_1 text,dot_2 text,dot_3 text,dot_4 text);");

        db.beginTransaction();
        try{
            int count=1;
            String[] letter1={"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
            String[] initial={"in0","in1","in2","in3","in4","in5","in6","in7","in8","in9","in10","in11","in12","in13"};
            String[] fin={"fi0","fi1","fi2","fi3","fi4","fi5","fi6","fi7","fi8","fi9","fi10","fi11","fi12","fi13"};

            String[] letter2={"ㄲ","ㄸ","ㅃ","ㅆ","ㅉ"};
            String[] ssang_in={"in0","in2","in5","in6","in8"};
            String ssang="ssang";

            String[] letter3={"ㅏ","ㅑ","ㅓ","ㅕ","ㅗ","ㅛ","ㅜ","ㅠ","ㅡ","ㅣ","ㅐ","ㅔ","ㅖ","ㅘ","ㅚ","ㅝ","ㅢ","ㅒ","ㅙ","ㅞ","ㅟ"};
            String[] vowel={"vo0","vo1","vo2","vo3","vo4","vo5","vo6","vo7","vo8","vo9","vo10","vo11","vo12","vo13","vo14","vo15","vo16","vo17","vo18","vo19","vo20","vo21","vo22","vo23","vo24"};

            String string="@string/";
            String pic1;
            String pic2;
            long newRowId;
            for(int i=0;i<14;i++) {
                pic1 = string+initial[i];
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "초성");
                values.put("dot_num",1);
                values.put("dot_1",pic1);
                db.insert("Braille",null,values);
            }

            for(int i=0;i<14;i++) {
                pic1 = string+fin[i];
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "종성");
                values.put("dot_num",1);
                values.put("dot_1",pic1);
                db.insert("Braille",null,values);
            }

            for(int i=0;i<5;i++) {
                pic1 = string+ssang;
                pic2 = string+ssang_in[i];
                values.put("num", count++);
                values.put("letter", letter2[i]);
                values.put("type", "된소리초성");
                values.put("dot_num",2);
                values.put("dot_1",pic1);
                values.put("dot_2",pic2);
                db.insert("Braille",null,values);
            }

            values.put("num",count++);
            values.put("letter","ㄲ");
            values.put("type","된소리종성");
            values.put("dot_num",2);
            values.put("dot_1","@string/ssang_fi0");
            values.put("dot_2","@string/ssang_fi0");
            db.insert("Braille",null,values);
            values.put("dot_2", (String) null);


            values.put("num",count++);
            values.put("letter","ㅆ");
            values.put("type","된소리종성");
            values.put("dot_num",1);
            values.put("dot_1","@string/ssang_fi1");
            db.insert("Braille",null,values);
            values.put("dot_2", (String) null);



            for(int i=0;i<17;i++) {
                pic1 = string+vowel[i];
                values.put("num", count++);
                values.put("letter", letter3[i]);
                values.put("type", "중성");
                values.put("dot_num",1);
                values.put("dot_1",pic1);
                db.insert("Braille",null,values);
            }
            for(int i=17,j=17;i<25;i=i+2,j++) {

                pic1 = string+vowel[i];
                pic2 = string+vowel[i+1];
                values.put("num", count++);
                values.put("letter", letter3[j]);
                values.put("type", "중성");
                values.put("dot_num",2);
                values.put("dot_1",pic1);
                values.put("dot_2",pic2);
                db.insert("Braille",null,values);
            }



            db.setTransactionSuccessful();



        }catch(SQLiteException e){

        }finally {
            db.endTransaction();
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
