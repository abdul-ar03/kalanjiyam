package com.example.kalanjiyam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Admin on 2/27/2019.
 */


public class Farming_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farming_page);
    }

    public void add_product_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Farming_Add_Product.class);
        startActivityForResult(intent ,1111);
    }

    public void buy_product_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Farming_Buy_Product.class);
        startActivityForResult(intent ,2222);
    }
}
