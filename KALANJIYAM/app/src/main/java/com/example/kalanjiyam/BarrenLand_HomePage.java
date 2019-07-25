package com.example.kalanjiyam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class BarrenLand_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barren_land_page);
    }

    public void add_land_funct(View view){
        Intent intent = new Intent(getApplicationContext(), BarrenLand_Add_Land.class);
        startActivityForResult(intent ,1111);
    }

    public void view_land_funct(View view){
        Intent intent = new Intent(getApplicationContext(), BarrenLand_View_Land.class);
        startActivityForResult(intent ,2222);
    }
}