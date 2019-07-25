package com.example.kalanjiyam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.kalanjiyam.dataModel.Land;

import java.util.ArrayList;


public class BarrenLand_Desc_Job extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barren_land_view_page);
        Land p = (Land) getIntent().getSerializableExtra("obj");
        TextView name_space=(TextView)findViewById(R.id.name_data);
        TextView cost_space=(TextView)findViewById(R.id.cost_data);
        TextView quantity_space=(TextView)findViewById(R.id.quantity_data);
        TextView contact_space=(TextView)findViewById(R.id.contact_data);
        TextView desc_space=(TextView)findViewById(R.id.desc_data);

        name_space.setText(p.getLocation());
        cost_space.setText(p.getOwner());
        quantity_space.setText(p.getType());
        contact_space.setText(p.getContact());
        desc_space.setText(p.getDescription());


    }
}

