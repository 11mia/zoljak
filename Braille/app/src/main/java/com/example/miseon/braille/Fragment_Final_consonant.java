package com.example.miseon.braille;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_Final_consonant extends Fragment{

    LinearLayout c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=null;
        view = inflater.inflate(R.layout.fragment_final_consonant,null);


        c1 = (LinearLayout)view.findViewById(R.id.c1);
        c2 = (LinearLayout)view.findViewById(R.id.c2);
        c3 = (LinearLayout)view.findViewById(R.id.c3);
        c4 = (LinearLayout)view.findViewById(R.id.c4);
        c5 = (LinearLayout)view.findViewById(R.id.c5);
        c6 = (LinearLayout)view.findViewById(R.id.c6);
        c7 = (LinearLayout)view.findViewById(R.id.c7);
        c8 = (LinearLayout)view.findViewById(R.id.c8);
        c9 = (LinearLayout)view.findViewById(R.id.c9);
        c10 = (LinearLayout)view.findViewById(R.id.c10);
        c11 = (LinearLayout)view.findViewById(R.id.c11);
        c12 = (LinearLayout)view.findViewById(R.id.c12);
        c13 = (LinearLayout)view.findViewById(R.id.c13);
        c14 = (LinearLayout)view.findViewById(R.id.c14);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity root = getActivity();
                // Toast.makeText(root,"hello!",Toast.LENGTH_LONG).show();
                ((StudyConsonantActivity)getActivity()).clickConsonant(253,254);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(255,256);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(257,258);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(259,260);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(261,262);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(263,264);
            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(265,266);
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(267,268);
            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(269,270);
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(271,272);
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(273,274);
            }
        });

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(275,276);
            }
        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(277,278);
            }
        });

        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(279,280);
            }
        });

        return view;
    }
}
