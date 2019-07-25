package com.example.kalanjiyam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Admin on 2/27/2019.
 */

public class Employment_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_page);
    }

    public void add_job_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Employment_Add_Job.class);
        startActivityForResult(intent ,1111);
    }

    public void view_job_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Employment_View_Job.class);
        intent.putExtra("u_id","farmer");
        startActivityForResult(intent ,2222);
    }

    public void job_applied_fun(View view){
        Intent intent = new Intent(getApplicationContext(), Employment_Applied_Job.class);
        startActivityForResult(intent ,2222);
    }
}