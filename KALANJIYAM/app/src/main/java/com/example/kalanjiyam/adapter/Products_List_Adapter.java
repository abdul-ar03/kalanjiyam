package com.example.kalanjiyam.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalanjiyam.R;
import com.example.kalanjiyam.dataModel.Product;

import java.util.ArrayList;


public class Products_List_Adapter extends ArrayAdapter {
    private Context context;
    private int type;
    private ArrayList<Product> Products = new ArrayList<Product>();

    public Products_List_Adapter(Context context, ArrayList<Product> values, int type) {
        super(context, 0, values);
        this.context = context;
        this.Products = values;
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
        v = Inflater.inflate(R.layout.farming_list_item, null);
        TextView name_space = (TextView) v.findViewById(R.id.name_space);
        TextView type_space = (TextView) v.findViewById(R.id.type_space);
        TextView quantity_space = (TextView) v.findViewById(R.id.quantity_space);
        Product p = new Product();
        p = Products.get(position);
        name_space.setText(p.getName());
        type_space.setText(p.getType());
        quantity_space.setText(p.getQuality());
        return v;
    }


}

