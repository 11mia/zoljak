package com.example.miseon.braille;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_Ssang extends Fragment {

    LinearLayout s1,s2,s3,s4,s5,s6,s7;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=null;
        view = inflater.inflate(R.layout.fragment_ssang,null);

        s1 = (LinearLayout)view.findViewById(R.id.s1);
        s2 = (LinearLayout)view.findViewById(R.id.s2);
        s3 = (LinearLayout)view.findViewById(R.id.s3);
        s4 = (LinearLayout)view.findViewById(R.id.s4);
        s5 = (LinearLayout)view.findViewById(R.id.s5);
        s6 = (LinearLayout)view.findViewById(R.id.s6);
        s7 = (LinearLayout)view.findViewById(R.id.s7);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(281,282);
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(283,284);
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(285,286);
            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(287,288);
            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(289,290);
            }
        });

        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(291,292);
            }
        });

        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StudyConsonantActivity)getActivity()).clickConsonant(293,294);
            }
        });




        return view;
    }
}
