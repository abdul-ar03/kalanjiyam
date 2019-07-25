package com.example.kalanjiyam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalanjiyam.R;
import com.example.kalanjiyam.dataModel.Job;
import com.example.kalanjiyam.dataModel.Land;

import java.util.ArrayList;

/**
 * Created by Admin on 3/2/2019.
 */

public class Lands_List_Adapter extends ArrayAdapter {
    private Context context;
    private int type;
    private ArrayList<com.example.kalanjiyam.dataModel.Land> Land = new ArrayList<Land>();

    public Lands_List_Adapter(Context context, ArrayList<Land> values, int type) {
        super(context, 0, values);
        this.context = context;
        this.Land = values;
        this.type=type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if (v == null) {

        } else {
            v = convertView;
        }
        v = Inflater.inflate(R.layout.barren_land_list_item, null);
        TextView name_space = (TextView) v.findViewById(R.id.owner_space);
        TextView type_space = (TextView) v.findViewById(R.id.type_space);
        TextView quantity_space = (TextView) v.findViewById(R.id.loc_space);
        Land p = new Land();
        p = Land.get(position);
        name_space.setText(p.getOwner());
        type_space.setText(p.getType());
        quantity_space.setText(p.getLocation());
        return v;
    }


}


