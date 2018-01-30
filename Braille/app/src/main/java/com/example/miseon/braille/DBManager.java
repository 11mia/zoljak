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
            String[] initial={"c777477","c177477","c727477","c777757","c177757","c777457","c777776","c127457","c777476","c777756","c127477","c127757","c177457","c727457"};
            String[] fin={"c177777","c727757","c773757","c727777","c727776","c127777","c773777","c723756","c173777","c723777","c723757","c723776","c727756","c773756"};


            String[] letter2={"ㄲ","ㄸ","ㅃ","ㅆ","ㅉ"};
            String[] ssang_in={"c777477","c727477","c777457","c777776","c777476"};



            String[] letter3={"ㅏ","ㅑ","ㅓ","ㅕ","ㅗ","ㅛ","ㅜ","ㅠ","ㅡ","ㅣ","ㅐ","ㅔ","ㅖ","ㅘ","ㅚ","ㅝ","ㅢ","ㅒ","ㅙ","ㅞ","ㅟ"};
            String[] vowel={"c127776","c773457","c723477","c177756","c173776","c773476","c173477","c177476","c727476","c173757","c123757","c173457","c773477","c123776","c173456","c123477","c727456","c773457","c123757","c123776","c123757","c123477","c123757","c173477","c123757"};


            String[] letter4={"A","B","C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            String[] letter4_1={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
            String[] alpha_fir={"c777776"};
            String[] alpha={"c177777", "c127777","c177477","c177457","c177757","c127477","c127457","c127757","c727477","c727457","c173777","c123777","c173477"
            ,"c173457","c173757","c123477","c123457","c123757","c723477","c723457","c173776","c123776","c727456","c173476","c173456","c173756"};



            String[] letter5={"0","1","2","3","4","5","6","7","8","9"};
            String[] num_fir={"c773456"};
            String[] num={"c727457","c177777","c127777","c177477","c177457","c177757","c127477","c127457","c127757","c727477"};



            String[] letter6={".", "?", "!", ",", "“", "”", "’"};
            String[] symbol={"c727756","c723776","c723757","c777757","c723776","c773756","c773777"};

            String[] letter6_2={"·", ":",";","/","‘","’","(",")","{","}","[","]","~","*","〃","￦","￠","$","￡","￥","€"};
            String[] symbol_2_front={"c777757","c777757","c777756","c777456","c777776","c773756","c723776","c777776","c723776","c777757","c723776","c777756",
                    "c773776","c773757","c777756","c777477","c777477","c777477","c777477","c777477","c777477"};
            String[] symbol_2_back={"c723777","c727777","c723777","c773477","c723776","c773777","c773777","c773756","c727777","c773756",
            "c723777","c773756","c773776","c773757","c723777","c727456","c177477","c177457","c123777","c173456","c177757"};

            String[] letter6_3={"······","......"};
            String[] symbol_3={"c777776","c727756"};


            long newRowId;
            for(int i=0;i<14;i++) {
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "초성");
                values.put("dot_num",1);
                values.put("dot_1",initial[i]);
                db.insert("Braille",null,values);
            }

            for(int i=0;i<14;i++) {
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "종성");
                values.put("dot_num",1);
                values.put("dot_1",fin[i]);
                db.insert("Braille",null,values);
            }

            for(int i=0;i<5;i++) {
                values.put("num", count++);
                values.put("letter", letter2[i]);
                values.put("type", "된소리초성");
                values.put("dot_num",2);
                values.put("dot_1","c777776");
                values.put("dot_2",ssang_in[i]);
                db.insert("Braille",null,values);
            }

            values.put("num",count++);
            values.put("letter","ㄲ");
            values.put("type","된소리종성");
            values.put("dot_num",2);
            values.put("dot_1","c177777");
            values.put("dot_2","c177777");
            db.insert("Braille",null,values);


            values.put("num",count++);
            values.put("letter","ㅆ");
            values.put("type","된소리종성");
            values.put("dot_num",1);
            values.put("dot_1","c773477");
            values.put("dot_2", (String) null);
            db.insert("Braille",null,values);



            for(int i=0;i<17;i++) {
                values.put("num", count++);
                values.put("letter", letter3[i]);
                values.put("type", "중성");
                values.put("dot_num",1);
                values.put("dot_1",vowel[i]);
                db.insert("Braille",null,values);
            }

            for(int i=17,j=17;i<25;i=i+2,j++) {
                values.put("num", count++);
                values.put("letter", letter3[j]);
                values.put("type", "중성");
                values.put("dot_num",2);
                values.put("dot_1", vowel[i]);
                values.put("dot_2",vowel[i+1]);
                db.insert("Braille",null,values);
            }

            for(int i=0; i<26; i++) {
                values.put("num", count++);
                values.put("letter", letter4[i]);
                values.put("type", "알파벳대문자");
                values.put("dot_num", 2);
                values.put("dot_1", alpha_fir[0]);
                values.put("dot_2",alpha[i]);
                db.insert("Braille", null, values);
            }

            for(int i=0; i<26; i++) {
                values.put("num", count++);
                values.put("letter", letter4_1[i]);
                values.put("type", "알파벳소문자");
                values.put("dot_num", 1);
                values.put("dot_1",alpha[i]);
                values.put("dot_2", (String) null);
                db.insert("Braille", null, values);
            }

            for (int i=0; i<10; i++ ) {
                values.put("num", count++);
                values.put("letter",letter5[i]);
                values.put("type","숫자");
                values.put("dot_num",2);
                values.put("dot_1", num_fir[0]);
                values.put("dot_2",num[i]);
                db.insert("Braille", null, values);
            }

            values.put("num", count++);
            values.put("letter","10");
            values.put("type", "숫자");
            values.put("dot_num",3);
            values.put("dot_1", num_fir[0]);
            values.put("dot_2", num[1]);
            values.put("dot_3", num[0]);
            db.insert("Braille", null, values);


            for(int i=0; i<7; i++) {
                values.put("num", count++);
                values.put("letter",letter6[i]);
                values.put("type","문장부호");
                values.put("dot_num",1);
                values.put("dot_1", symbol[i]);
                values.put("dot_2", (String) null);
                values.put("dot_3", (String) null);
                db.insert("Braille", null, values);
            }

            for (int i=0; i<21; i++) {
                values.put("num", count++);
                values.put("letter",letter6_2[i]);
                values.put("type","문장부호");
                values.put("dot_num",2);
                values.put("dot_1", symbol_2_front[i]);
                values.put("dot_2", symbol_2_back[i]);
                values.put("dot_3", (String) null);
                db.insert("Braille", null, values);
            }


           for(int i=0; i<2; i++) {
               values.put("num", count++);
               values.put("letter", letter6_3[i]);
               values.put("type", "문장부호");
               values.put("dot_num", 3);
               values.put("dot_1", symbol_3[i]);
               values.put("dot_2", symbol_3[i]);
               values.put("dot_3", symbol_3[i]);
               db.insert("Braille", null, values);
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
