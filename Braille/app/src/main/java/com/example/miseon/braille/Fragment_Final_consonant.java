package com.example.miseon.braille;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Final_consonant extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=null;
        view = inflater.inflate(R.layout.fragment_final_consonant,null);
        return view;
    }
}