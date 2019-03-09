package com.example.KVectorApp.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MagazinesAdapter extends ArrayAdapter<Magazine> {
    int resource;

    public MagazinesAdapter(Context context, int res, ArrayList<Magazine> magazines) {
        super(context, res, magazines);
        this.resource = res;
    }

    public void update(Magazine[] magazines) {
        clear();
        addAll(magazines);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getTitle();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(this.resource, parent, false);
        }

        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(title);

        return convertView;
    }


}
