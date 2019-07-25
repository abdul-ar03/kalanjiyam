package com.example.kalanjiyam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kalanjiyam.dataModel.Product;

/**
 * Created by Admin on 2/27/2019.
 */

public class Home_page extends AppCompatActivity {
    String value1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("u_id");
        if(value1.equals("farmer1") || value1.equals("farmer2") || value1.equals("farmer3") ){
            setContentView(R.layout.home_page);
        }
        else{
            setContentView(R.layout.home_page2);
        }

    }

    public void view_farm_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Farming_Buy_Product.class);
        startActivityForResult(intent ,5555);
    }

    public void view_job_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Employment_View_Job.class);
        intent.putExtra("u_id",value1);
        startActivityForResult(intent ,5555);
    }

    public void farming_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Farming_HomePage.class);
        startActivityForResult(intent ,1111);
    }

    public void empolyment_funct(View view){
        Intent intent = new Intent(getApplicationContext(), Employment_HomePage.class);

        startActivityForResult(intent ,2222);
    }

    public void barren_funct(View view){
        Intent intent = new Intent(getApplicationContext(), BarrenLand_HomePage.class);
        startActivityForResult(intent ,3333);
    }

    public void logout_funct(View view){
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("key_commit", false);
        editor.commit();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
