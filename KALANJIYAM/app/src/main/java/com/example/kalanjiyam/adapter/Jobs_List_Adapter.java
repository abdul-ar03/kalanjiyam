package com.example.kalanjiyam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalanjiyam.Employment_View_Job;
import com.example.kalanjiyam.R;
import com.example.kalanjiyam.dataModel.Job;
import com.example.kalanjiyam.dataModel.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 3/2/2019.
 */

public class Jobs_List_Adapter extends ArrayAdapter {
    private Context context;
    private int type;
    private ArrayList<Job> Job = new ArrayList<Job>();

    public Jobs_List_Adapter(Context context, ArrayList<Job> values, int type) {
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
        v = Inflater.inflate(R.layout.employment_list_items, null);
        TextView name_space = (TextView) v.findViewById(R.id.deprt_space);
        TextView type_space = (TextView) v.findViewById(R.id.post_space);
        TextView quantity_space = (TextView) v.findViewById(R.id.vac_space);
        Job p = new Job();
        p = Job.get(position);
        name_space.setText(p.getDepartment());
        type_space.setText(p.getPost());
        quantity_space.setText(p.getVacancies());
        return v;
    }


}


