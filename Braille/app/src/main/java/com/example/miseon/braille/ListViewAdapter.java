package com.example.miseon.braille;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by miseon on 2018-02-24.
 */

public class ListViewAdapter extends BaseAdapter {
    private  ArrayList<ListVO> listVO = new ArrayList<ListVO>();
    public ListViewAdapter(){}
    @Override
    public int getCount(){
        return listVO.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.libname);
        TextView address=(TextView) convertView.findViewById(R.id.address);
        ListVO listViewItem= listVO.get(position);

        name.setText(listViewItem.getname());
        address.setText(listViewItem.getaddress());

        return convertView;
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listVO.get(position);
    }

    public void addVO(String name,String address){
        ListVO item = new ListVO();
        item.setname(name);
        item.setaddress(address);
        listVO.add(item);

    }



}
