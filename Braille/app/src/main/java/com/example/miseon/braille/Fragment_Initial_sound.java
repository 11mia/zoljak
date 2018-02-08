package com.example.miseon.braille;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_Initial_sound extends Fragment{
    private Context context;

    LinearLayout c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=null;
        view = inflater.inflate(R.layout.fragment_initial_sound,null);
      //  context = container.getContext();


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
                ((StudyConsonantActivity)getActivity()).clickConsonant(183,184);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(185,186);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(187,188);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(189,190);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(191,192);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(193,194);
            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(195,196);
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(197,198);
            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(199,200);
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(201,202);
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(203,204);
            }
        });

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(205,206);
            }
        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(207,208);
            }
        });

        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(209,210);
            }
        });


        return view;
    }



}
