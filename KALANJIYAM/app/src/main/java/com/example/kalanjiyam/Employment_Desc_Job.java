package com.example.kalanjiyam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kalanjiyam.dataModel.Job;
import com.example.kalanjiyam.dataModel.Product;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 3/2/2019.
 */

public class Employment_Desc_Job extends AppCompatActivity {
    private ArrayList<Job> J_array = new ArrayList<Job>();
    String value1="";
    String data1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_view_job);
        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("u_id");

        if (value1.equals("user1") || value1.equals("user2") || value1.equals("user3")){
            Button button=(Button)findViewById(R.id.button6);
            button.setVisibility(View.VISIBLE);
        }

        Job p = (Job) getIntent().getSerializableExtra("obj");
        TextView type_space=(TextView)findViewById(R.id.type_space);
        TextView name_space=(TextView)findViewById(R.id.name_space);
        TextView cost_space=(TextView)findViewById(R.id.cost_space);
        TextView quantity_space=(TextView)findViewById(R.id.quantity_space);
        TextView contact_space=(TextView)findViewById(R.id.contact_space);
        TextView desc_space=(TextView)findViewById(R.id.desc_space);

        type_space.setText(p.getDepartment());
        name_space.setText(p.getPost());
        cost_space.setText(p.getVacancies());
        quantity_space.setText(p.getSalary());
        contact_space.setText(p.getContact());
        desc_space.setText(p.getDescription());

        data1=p.getDepartment()+" - "+p.getPost();

    }

    public void getJob(View view){
        Intent intent = new Intent(getApplicationContext(), Get_Job.class);
        intent.putExtra("data1",data1);
        startActivityForResult(intent ,1111);
    }
}
