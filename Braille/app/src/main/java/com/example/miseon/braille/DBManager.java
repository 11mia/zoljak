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








            String[] letterword_short1={"가","나","다","마","바","사","자","카","타","파","하",
                    "억","언","얼","연","열","영","옥","온","옹","운","울","은","을","인"};//25
            String []shortletter1_img1={"c127476","c177477","c727477","c177757","c777457","c123777","c777476","c127477","c127757","c177457","c727457",
                    "c177456","c723456","c723457","c177776","c127756","c127456","c173476","c123756","c123456","c127457","c123476","c173756","c723476","c123457" };



            String[] letterword_short2={"것","그래서","그러나","그러면","그러므로","그런데","그리고","그리하여"};//8
            String [] shortletter2_img1={"c777456","c177777","c177777","c177777","c177777","c177777","c177777","c177777"};
            String[] shortletter2_img2={"c723477","c723477","c177477","c727757","c727776","c173457","c173776","c177756"};




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


            String []letter_word_cho={"강","가방","나라","나비","달","돌","아래", "자리", "말","물","불","밥","사과","솔", "아이","우유","점자","작다","차","책","코","카드", "탑","톱","파","포도",
                    "한글","조합"};
            int [] dot_num_cho={2,3,3,3,2,3,3,3,2,2,2,2,3,3,2,2,4,3,2,3,2,3,2,3,1,4,4,4};

            String [] cho_word_img_1={"c127476","c127476","c177477","c177477","c727477","c727477","c127776","c777476","c177757","c177757","c777457","c777457",
                    "c123777","c777776","c127776","c173477","c777476","c777476","c777756","c777756","c127477","c127477","c127757","c127757","c177457","c177457","c727457","c777476"};

            String [] cho_word_img_2={"c723756","c777457","c777757","c777457","c727777","c173776","c777757","c777757","c727777","c123476","c123476","c127777","c777477","c173776",
                    "c173757","c177476","c723477","c177777","c127776","c123757","c173776","c727477","c127777","c173776",null,"c173776",
                    "c727757","c173776"};

            String [] cho_word_img_3={null,"c723756","c127776","c173757",null,"c727777","c123757",
                    "c173757",null,null,null,null,"c123776","c727777",
                    null,null,"c727776","c727477",null,"c177777",null,"c727476",null,"c127777",null,"c727477","c777477","c727457"};

            String [] cho_word_img_4={null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"c777476",null,null,null,null,null,null,null,null,"c173776",
                    "c723476","c127777"};






            String[] letterword_joong={"아기","아이","약","약속","법","정성","여름","겨울","오리","봄",
                    "표기","효소","국","우리","유리","우유","어른","가을","침","일","개","해","얘","걔",
                    "게","체계","아예","도예","왕","소화","왜","인쇄","월","소원",
                    "쉘","웹","위선","바위","의사","의자","소외","외과"};

            int dot_num_joong[]={3,2,2,4,3,4,4,3,3,3,
                    4,4,3,3,3,2,3,2,3,2,2,2,2,3,
                    2,4,2,3,2,4,2,4,2,4,
                    4,3,4,4,2,2,3,3};

            String [] joongword_img_1={"c127776","c127776","c773457","c773457","c777457","c777476","c177756","c777477","c173776","c777457",
                    "c177457","c727457","c777477","c173477","c177476","c173477","c723477","c127476","c777756","c173757","c777477","c727457","c773457","c777477",
                    "c777477","c777756","c127776","c727477","c123776","c777776","c123776","c123457","c123477","c777776",
                    "c777776","c123477","c173477","c777457","c727456","c727456","c777776","c173456"};

            String [] joongword_img_2={"c777477","c173757","c177777","c177777","c723477","c127456","c777757","c177756","c777757","c173776",
                    "c773476","c773476","c173477","c777757","c777757","c177476","c777757","c723476","c173757","c727777","c123757","c123757","c123757","c773457",
                    "c173457","c173457","c773477","c173776","c723756","c173776","c123757","c777776","c727777","c173776",
                    "c123477","c123757","c123757","c127776","c123777","c777476","c173776","c777477"};

            String [] joongword_img_3={"c173757",null,null,"c777776","c127777","c777776","c727476","c123476","c173757","c727777",
                    "c777477","c777776","c177777","c173757","c173757",null,"c173756",null,"c727776",null,null,null,null,"c123757",
                    null,"c777477",null,"c773477",null,"c727457",null,"c123776",null,"c123477",
                    "c123757","c127777","c777776","c173477",null,null,"c173456","c123776"};

            String [] joongword_img_4={null,null,null,"c173476",null,"c127456","c727776",null,null,null,
                    "c173757","c173776",null,null,null,null,null,null,null,null,null,null,null,null,
                    null,"c773477",null,null,null,"c123776",null,"c123757",null,"c727757",
                    "c727777",null,"c723456","c123757",null,null,null,null};



            String []letterword_jong={"걱정","국","난","돈","닫다","듣다","달","말","마음","엄마","밥","법","벗","맛",
                    "응","영어","찾다","갖다","빛","꽃","부엌","녘","같다","낱말","앞","옆",
                    "낳다","좋다"};
            int [] dot_num_jong={4,3,3,2,3,4,2,2,4,3,2,3,3,2,
                    2,2,4,3,3,4,4,3,3,4,2,2,
                    3,4};

            String []jongword_img_1={"c777477","c777477","c177477","c727477","c727477","c727477","c727477","c177757","c177757","c723477","c777457","c777457","c777457","c177757",
                    "c727476","c127456","c777756","c127476","c777457","c777776","c777457","c177477","c127476","c177477","c127776","c177756",
                    "c177477","c777476"};

            String []jongword_img_2={"c177456","c173477","c127776","c123756","c773757","c727476","c727777","c727777","c127776","c727776","c127777","c723477","c723477","c773777",
                    "c723756","c723477","c127776","c173777","c173757","c777477","c173477","c177756","c723776","c723776","c727756","c727756",
                    "c773756","c173776"};

            String []jongword_img_3={"c777476","c177777","c727757",null,"c727477","c773757",null,null,"c727476","c177757",null,"c127777","c773777",null,
                    null,null,"c173777","c727477","c723777","c173776","c723477","c723757","c727477","c177757",null,null,
                    "c727477","c773756"};
            String []jongword_img_4={"c127456",null,null,null,null,"c727477",null,null,"c727776",null,null,null,null,null,
                    null,null,"c727477",null,null,"c723777","c723757",null,null,"c727777",null,null,
                    null,"c727477"};



            String []letterword_ssangcho={"꿀","꽃","딸","떡","뿔", "빵","쑥","싸다","짜다","날짜"};
            int[] dot_num_ssangcho={3,4,3,3,3,3,4,3,3,4};
            String [] ssangcho_img_1={"c777776","c777776","c777776","c777776","c777776","c777776","c777776,","c777776","c777776","c177477"};
            String [] ssangcho_img_2={"c777477","c777477","c727477","c727477","c777457","c777457","c777776","c777776","c777476","c127776"};
            String [] ssangcho_img_3={"c123476","c173776","c727777","c177456","c123476","c723756","c173477","c727477","c727477","c777776"};
            String [] ssangcho_img_4={null,"c723777",null,null,null,null,"c177777",null,null,"c777476"};




            String []letterword_ssangjong={"밖","닦다","있다","샀다"};
            int[] dot_num_ssangjong={3,4,3,3};
            String [] ssangjong_img_1={"c777457","c727477","c173757","c123777"};
            String [] ssangjong_img_2={"c177777","c177777","c773477","c773477"};
            String [] ssangjong_img_3={"c177777","c177777","c727477","c727477"};
            String [] ssangjong_img_4={null,"c727477",null,null};




            String []letterword_alpha={"ant","bag","cat","dog","egg","fish","go","hot","ice","join","king","lion","mom","nose","open",
                    "pop","quiz","rap","sky","tea","unit","very","wall","fix","yes","zoo"};
            int[] dot_num_alpha={3,3,3,3,3,4,2,
                    3,3,4,4,4,3,
                    4,4,3,4,3,3,
                    3,4,4,4,3,3,3};


            String[] alphaword_img_1={"c177777", "c127777","c177477","c177457","c177757","c127477","c127457","c127757","c727477","c727457","c173777","c123777","c173477"
                    ,"c173457","c173757","c123477","c123457","c123757","c723477","c723457","c173776","c123776","c727456","c127477","c173456","c173756"};


            String [] alphaword_img_2={"c173457","c177777","c177777","c173757","c127457","c727477","c173757","c173757","c177477","c173757","c727477","c727477","c173757",
                    "c173757","c123477","c173757","c173776","c177777","c173777","c177757","c173457","c177757","c177777","c727477","c177757","c173757"};
            //t, g, t, g, g, s, null, t, e, i, n, o, m, s, e, p, i, p, y, a, i, r, l, x, s, o
            String [] alphaword_img_3={"c723457","c127457","c723457","c127457","c127457","c723477",null,"c723457","c177757","c727477","c173457","c173757","c173477",
                    "c723477","c177757","c123477","c727477","c123477","c173456",
                    "c177777","c727477","c123757","c123777","c173476","c723477","c173757"};
            String [] alphaword_img_4={null,null,null,null,null,"c127757",null,
                    null,null,"c173457","c127457","c173457",null,
                    "c177757","c173457",null,"c173756",null,null,
                    null,"c723457","c173456","c123777",null,null,null};



            // ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ변환초성19개
            // ㄱㄲㄳㄴㄵㄶㄷㄹㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ변환종성//27개

            String [] chosungT={"ㄱ", "ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
            int []dot_num_chosungT={1,2,1,1,2,1,1,1,2,1,2,1,1,2,1,1,1,1,1};
            String[]chosungT_img_1= {"c777477", "c777776", "c177477", "c727477", "c777776", "c777757", "c177757", "c777457", "c777776", "c777776", "c777776","c127457",//~ㅇ
            "c777476","c777776","c777756","c127477","c127757","c177457","c727457"};
            String[]chosungT_img_2={null,"c777477",null, null,"c727477",null, null, null,"c777457",null,"c777776",null, null,"c777476",null,null, null, null,null};



            String [] jongsungT={"ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ",
                    "ㄻ","ㄼ","ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ",
                    "ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
            int [] dot_num_jongsungT={1,2,2,1,2,2,1,1,2,
            2,2,2,2,2,2,1,1,2,
            1,1,1,1,1,1,1,1,1};
            String[]jongsungT_img_1={"c177777","c177777","c177777","c727757","c727757","c727757","c773757","c727777","c727777",
            "c727777","c727777","c727777","c727777","c727777","c727777","c727776","c127777","c127777",
            "c773777","c773477","c723756","c173777","c723777","c723757","c723776","c727756","c773756"};


            String[]jongsungT_img_2={null,"c177777","c773777",null, "c173777","c773756",null, null, "c177777",
            "c727776","c127777","c773777","c723776","c727756","c773756",null, null,"c773777",
            null,null,null,null,null,null,null,null,null};


            for(int i=0;i<14;i++) {
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "초성");
                values.put("dot_num",1);
                values.put("dot_1",initial[i]);
                values.put("dot_2",(String)null);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille",null,values);
            }

            for(int i=0;i<14;i++) {
                values.put("num", count++);
                values.put("letter", letter1[i]);
                values.put("type", "종성");
                values.put("dot_num",1);
                values.put("dot_1",fin[i]);
                values.put("dot_2",(String)null);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille",null,values);
            }

            for(int i=0;i<5;i++) {
                values.put("num", count++);
                values.put("letter", letter2[i]);
                values.put("type", "된소리초성");
                values.put("dot_num",2);
                values.put("dot_1","c777776");
                values.put("dot_2",ssang_in[i]);

                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille",null,values);
            }

            values.put("num",count++);
            values.put("letter","ㄲ");
            values.put("type","된소리종성");
            values.put("dot_num",2);
            values.put("dot_1","c177777");
            values.put("dot_2","c177777");

            values.put("dot_3",(String)null);
            values.put("dot_4",(String)null);

            db.insert("Braille",null,values);


            values.put("num",count++);
            values.put("letter","ㅆ");
            values.put("type","된소리종성");
            values.put("dot_num",1);
            values.put("dot_1","c773477");
            values.put("dot_2", (String) null);

            values.put("dot_3",(String)null);
            values.put("dot_4",(String)null);

            db.insert("Braille",null,values);



            for(int i=0;i<17;i++) {
                values.put("num", count++);
                values.put("letter", letter3[i]);
                values.put("type", "중성");
                values.put("dot_num",1);
                values.put("dot_1",vowel[i]);
                values.put("dot_2",(String)null);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille",null,values);
            }

            for(int i=17,j=17;i<25;i=i+2,j++) {
                values.put("num", count++);
                values.put("letter", letter3[j]);
                values.put("type", "중성");
                values.put("dot_num",2);
                values.put("dot_1", vowel[i]);
                values.put("dot_2",vowel[i+1]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille",null,values);
            }



            for(int i=0; i<25;i++) {
                values.put("num",count++);
                values.put("letter",letterword_short1[i]);
                values.put("type","한글약어");
                values.put("dot_num",1);
                values.put("dot_1",shortletter1_img1[i]);
                values.put("dot_2",(String)null);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
                db.insert("Braille",null,values);

            }

            for(int i=0; i<8; i++) {
                values.put("num", count++);
                values.put("letter",letterword_short2[i]);
                values.put("type","한글약어");
                values.put("dot_num",2);
                values.put("dot_1",shortletter2_img1[i]);
                values.put("dot_2",shortletter2_img2[i]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
                db.insert("Braille",null,values);

            }


            for(int i=0; i<26; i++) {
                values.put("num", count++);
                values.put("letter", letter4[i]);
                values.put("type", "대문자");
                values.put("dot_num", 2);
                values.put("dot_1", alpha_fir[0]);
                values.put("dot_2",alpha[i]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
                db.insert("Braille", null, values);
            }

            for(int i=0; i<26; i++) {
                values.put("num", count++);
                values.put("letter", letter4_1[i]);
                values.put("type", "소문자");
                values.put("dot_num", 1);
                values.put("dot_1",alpha[i]);
                values.put("dot_2", (String) null);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
                db.insert("Braille", null, values);
            }

            for (int i=0; i<10; i++ ) {
                values.put("num", count++);
                values.put("letter",letter5[i]);
                values.put("type","숫자");
                values.put("dot_num",2);
                values.put("dot_1", num_fir[0]);
                values.put("dot_2",num[i]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);

                db.insert("Braille", null, values);
            }

            values.put("num", count++);
            values.put("letter","10");
            values.put("type", "숫자");
            values.put("dot_num",3);
            values.put("dot_1", num_fir[0]);
            values.put("dot_2", num[1]);
            values.put("dot_3", num[0]);
            values.put("dot_4",(String)null);
            db.insert("Braille", null, values);


            for(int i=0; i<7; i++) {
                values.put("num", count++);
                values.put("letter",letter6[i]);
                values.put("type","문장부호");
                values.put("dot_num",1);
                values.put("dot_1", symbol[i]);
                values.put("dot_2", (String) null);
                values.put("dot_3", (String) null);
                values.put("dot_4",(String)null);

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
                values.put("dot_4",(String)null);

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
                values.put("dot_4",(String)null);

                db.insert("Braille", null, values);
            }




            for (int i=0; i<28;i++) {
                values.put("num",count++);
                values.put("letter",letter_word_cho[i]);
                values.put("type","단어초성");
                values.put("dot_num",dot_num_cho[i]);
                values.put("dot_1", cho_word_img_1[i]);
                values.put("dot_2", cho_word_img_2[i]);
                values.put("dot_3", cho_word_img_3[i]);
                values.put("dot_4", cho_word_img_4[i]);

                db.insert("Braille",null,values);

            }

            for(int i=0; i<42; i++) {
                values.put("num", count++);
                values.put("letter", letterword_joong[i]);
                values.put("type", "단어중성");
                values.put("dot_num", dot_num_joong[i]);
                values.put("dot_1",joongword_img_1[i]);
                values.put("dot_2",joongword_img_2[i]);
                values.put("dot_3",joongword_img_3[i]);
                values.put("dot_4",joongword_img_4[i]);

                db.insert("Braille", null, values);
            }

            for(int i=0; i<28; i++) {
                values.put("num", count++);
                values.put("letter", letterword_jong[i]);
                values.put("type", "단어종성");
                values.put("dot_num", dot_num_jong[i]);
                values.put("dot_1",jongword_img_1[i]);
                values.put("dot_2",jongword_img_2[i]);
                values.put("dot_3",jongword_img_3[i]);
                values.put("dot_4",jongword_img_4[i]);

                db.insert("Braille", null, values);

            }
            for( int i=0; i<10; i++) {
                values.put("num", count++);
                values.put("letter", letterword_ssangcho[i]);
                values.put("type", "단어쌍자음초성");
                values.put("dot_num", dot_num_ssangcho[i]);
                values.put("dot_1",ssangcho_img_1[i]);
                values.put("dot_2",ssangcho_img_2[i]);
                values.put("dot_3",ssangcho_img_3[i]);
                values.put("dot_4",ssangcho_img_4[i]);

                db.insert("Braille", null, values);
            }

            for (int i=0; i<4; i++) {
                values.put("num", count++);
                values.put("letter", letterword_ssangjong[i]);
                values.put("type", "단어쌍자음종성");
                values.put("dot_num", dot_num_ssangjong[i]);
                values.put("dot_1",ssangjong_img_1[i]);
                values.put("dot_2",ssangjong_img_2[i]);
                values.put("dot_3",ssangjong_img_3[i]);
                values.put("dot_4",ssangjong_img_4[i]);

                db.insert("Braille", null, values);
            }

            for (int i=0; i<26; i++) {
                values.put("num", count++);
                values.put("letter", letterword_alpha[i]);
                values.put("type", "단어알파벳");
                values.put("dot_num", dot_num_alpha[i]);
                values.put("dot_1", alphaword_img_1[i]);
                values.put("dot_2",alphaword_img_2[i]);
                values.put("dot_3",alphaword_img_3[i]);
                values.put("dot_4",alphaword_img_4[i]);

                db.insert("Braille", null, values);
            }


            values.put("num",count++);
            values.put("letter", "숫자표");
            values.put("type", "숫자표");
            values.put("dot_num", 1);
            values.put("dot_1", "c773456");
            values.put("dot_2",(String)null);
            values.put("dot_3",(String)null);
            values.put("dot_4",(String)null);
            db.insert("Braille", null, values);

            values.put("num",count++);
            values.put("letter","된소리초성표");
            values.put("type","된소리초성표");
            values.put("dot_num",1);
            values.put("dot_1","c777776");
            values.put("dot_2",(String)null);
            values.put("dot_3",(String)null);
            values.put("dot_4",(String)null);
            db.insert("Braille", null, values);

            values.put("num", count++);
            values.put("letter","알파벳대문자표");
            values.put("type", "알파벳대문자표");
            values.put("dot_num",1);
            values.put("dot_1","c777776");
            values.put("dot_2",(String)null);
            values.put("dot_3",(String)null);
            values.put("dot_4",(String)null);
            db.insert("Braille", null, values);

           for(int i=0; i<19; i++) {
                values.put("num", count++);
                values.put("letter",chosungT[i]);
                values.put("type","변환초성");
                values.put("dot_num",dot_num_chosungT[i]);
                values.put("dot_1",chosungT_img_1[i]);
                values.put("dot_2",chosungT_img_2[i]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
                db.insert("Braille", null, values);
            }

            for(int i=0; i<27; i++) {
                values.put("num", count++);
                values.put("letter",jongsungT[i]);
                values.put("type","변환종성");
                values.put("dot_num",dot_num_jongsungT[i]);
                values.put("dot_1",jongsungT_img_1[i]);
                values.put("dot_2",jongsungT_img_2[i]);
                values.put("dot_3",(String)null);
                values.put("dot_4",(String)null);
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
