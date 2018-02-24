package com.example.miseon.braille;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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

    public void goToRule(View v){

        try {
            boolean bResult = exist("brailleRule.pdf");
            Log.d("checkFile", "file Check="+bResult);
            if(!bResult){
                CopyReadAssets();
            }else{

            }
        } catch (Exception e) {

        }
        openPDF();

    }

    private void CopyReadAssets()
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "brailleRule.pdf");
        try
        {
            in = assetManager.open("brailleRule.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }


    public boolean exist(String srcFile){
        File file = new File(getFilesDir(), "brailleRule.pdf");

        if (file.exists()) {
            return true;
        }

        return false;

    }

    public void openPDF(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(getFilesDir(), "brailleRule.pdf");
/*

        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/brailleRule.pdf"),"application/pdf");
*/
        Uri path  = Uri.fromFile(file);
        intent.setDataAndType(path,"application/pdf");

        try{
            startActivity(intent);
        }catch(ActivityNotFoundException ex){
            Toast.makeText(this,"PDF 파일을 보기 위한 뷰어 앱이 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }


    public void goToRealLife(View v){
        Intent it = new Intent(this,SupplementLifeActivity.class);
        startActivity(it);

    }

    public void goToLibrary(View v){
        Intent it = new Intent(this,supplementLibraryActivity.class);
        startActivity(it);

    }

}
