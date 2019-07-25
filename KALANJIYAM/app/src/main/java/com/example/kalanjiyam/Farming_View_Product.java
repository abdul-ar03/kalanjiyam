package com.example.kalanjiyam;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kalanjiyam.dataModel.Product;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Admin on 2/28/2019.
 */

public class Farming_View_Product extends AppCompatActivity {
    private ArrayList<Product> P_array = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farming_view_product);
        Product p = (Product) getIntent().getSerializableExtra("obj");
        TextView type_space=(TextView)findViewById(R.id.type_space);
        TextView name_space=(TextView)findViewById(R.id.name_space);
        TextView cost_space=(TextView)findViewById(R.id.cost_space);
        TextView quantity_space=(TextView)findViewById(R.id.quantity_space);
        TextView contact_space=(TextView)findViewById(R.id.contact_space);
        TextView desc_space=(TextView)findViewById(R.id.desc_space);

        type_space.setText(p.getType());
        name_space.setText(p.getName());
        cost_space.setText(p.getCost());
        quantity_space.setText(p.getQuality());
        contact_space.setText(p.getContact());
        desc_space.setText(p.getDescription());


    }
}
