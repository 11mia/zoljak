package com.example.miseon.braille;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.graphics.drawable.Drawable.*;
import static java.lang.Math.random;


public class TestContentActivity extends AppCompatActivity {
    int flag;
    final Context context = this;
    TextView tv;
    ImageView iv1, iv2, iv3, iv4;

    public static final int[] ran= {//imagebutton
            R.drawable.c127777, R.drawable.c727477, R.drawable.c177476,
                    R.drawable.c773757, R.drawable.c723756
    };//임시로 선언해준 배열임.필요없음.


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent);
        setTitle("테스트");

        Intent it = getIntent();
        flag = it.getIntExtra("flag", 1);

        tv = (TextView) findViewById(R.id.quizword);
        iv1 = (ImageView) findViewById((R.id.testimage1));
        iv2 = (ImageView) findViewById((R.id.testimage2));
        iv3 = (ImageView) findViewById((R.id.testimage3));
        iv4 = (ImageView) findViewById((R.id.testimage4));

        tv.setText("ㄷ");//문제보기 이렇게 나온다고 임시 설명

        iv1.setImageResource(ran[3]);
        iv2.setImageResource(ran[1]);
        iv3.setImageResource(ran[2]);
        iv4.setImageResource(ran[0]);//이렇게 나온다고 임시로 설명. 나중에는 랜덤하게!~


    }


    public void resultOX(View v){//이건 테스트할 때 나오는 버튼을 눌렀을떄 호출되는 함수입니다.그냥 임시로 적어놓음.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // 제목셋팅
        alertDialogBuilder.setTitle("답 결과");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("정답입니다.")
                .setCancelable(false)
                .setNeutralButton("닫기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();
    }


}
