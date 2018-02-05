package com.example.miseon.braille;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment_Initial_sound extends Fragment implements View.OnClickListener{
    private Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=null;
        view = inflater.inflate(R.layout.fragment_initial_sound,null);
        context = container.getContext();



        return view;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.c1:
             //   Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
                Activity root = getActivity();
                Toast.makeText(root,"hello!",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
