package com.example.miseon.braille;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class TestContentActivity2 extends AppCompatActivity {
    int flag;
    final Context context = this;
    ImageView iv1;
    Button b1, b2, b3, b4;

    public static final int[] ran= {//imagebutton
            R.drawable.c127777, R.drawable.c727477, R.drawable.c177476,
            R.drawable.c773757, R.drawable.c723756
    };//임시로 선언해준 배열임.필요없음.


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent2);
        setTitle("테스트(점자->글자)");
        Intent it = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        iv1 = (ImageView) findViewById((R.id.testjeomimage));//임시로 적어놓은거
        iv1.setImageResource(ran[3]);

        b1 = (Button) findViewById((R.id.testbutton1));
        b2 = (Button) findViewById((R.id.testbutton2));//임시로 적어놓은거
        b3 = (Button) findViewById((R.id.testbutton3));
        b4 = (Button) findViewById((R.id.testbutton4));

        b1.setText("ㄱ");
        b2.setText("ㅎ");//임시로 적어놓은거
        b3.setText("ㅇ");
        b4.setText("ㅋ");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void resultOX(View v){//이건 테스트할 때 나오는 버튼을 눌렀을떄 호출되는 함수입니다.그냥 임시로 적어놓음.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // 제목셋팅
        alertDialogBuilder.setTitle("답 결과");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("정답입니다")
                .setCancelable(false)
                .setPositiveButton("닫기",
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
