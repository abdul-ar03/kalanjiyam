package com.example.kalanjiyam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalanjiyam.R;
import com.example.kalanjiyam.dataModel.Job;
import com.example.kalanjiyam.dataModel.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 3/3/2019.
 */

public class Jobs_Applied_Adapter extends ArrayAdapter {
    private Context context;
    private int type;
    private ArrayList<com.example.kalanjiyam.dataModel.Product> Job = new ArrayList<Product>();

    public Jobs_Applied_Adapter(Context context, ArrayList<Product> values, int type) {
        super(context, 0, values);
        this.context = context;
        this.Job = values;
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
        v = Inflater.inflate(R.layout.jobs_list_items, null);
        TextView name_space = (TextView) v.findViewById(R.id.deprt_space);
        TextView type_space = (TextView) v.findViewById(R.id.post_space);
        TextView quantity_space = (TextView) v.findViewById(R.id.vac_space);
        Product p = new Product();
        p = Job.get(position);
        name_space.setText(p.getType());
        type_space.setText(p.getName());
        quantity_space.setText(p.getQuality());
        return v;
    }


}



