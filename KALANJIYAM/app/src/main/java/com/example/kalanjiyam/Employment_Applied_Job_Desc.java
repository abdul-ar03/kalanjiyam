package com.example.kalanjiyam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kalanjiyam.dataModel.Land;
import com.example.kalanjiyam.dataModel.Product;

/**
 * Created by Admin on 3/3/2019.
 */

public class Employment_Applied_Job_Desc extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_applied_desc);
        Product p = (Product) getIntent().getSerializableExtra("obj");
        TextView name_space=(TextView)findViewById(R.id.name_data);
        TextView cost_space=(TextView)findViewById(R.id.cost_data);
        TextView quantity_space=(TextView)findViewById(R.id.quantity_data);
        TextView email_data=(TextView)findViewById(R.id.email_data);
        TextView contact_space=(TextView)findViewById(R.id.contact_data);
        TextView desc_space=(TextView)findViewById(R.id.desc_data);

        name_space.setText(p.getType());
        cost_space.setText(p.getName());
        quantity_space.setText(p.getQuality());
        email_data.setText(p.getCost());
        contact_space.setText(p.getContact());
        desc_space.setText(p.getDescription());


    }
}
