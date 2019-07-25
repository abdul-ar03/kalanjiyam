package com.example.kalanjiyam;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kalanjiyam.dataModel.Job;
import com.example.kalanjiyam.dataModel.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Admin on 3/2/2019.
 */

public class Employment_Add_Job extends AppCompatActivity {
    private Dialog progress;
    public DatabaseReference myRef;
    public Job job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employment_add_job);
        job=new Job();
        progress= new Dialog(Employment_Add_Job.this);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.custome_progress_bar);
        myRef = FirebaseDatabase.getInstance().getReference("Employment_job");
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void add_job_funct(View view){
        LinearLayout cancel=(LinearLayout)progress.findViewById(R.id.cancel_layout);
        LinearLayout prog=(LinearLayout)progress.findViewById(R.id.progress_layout);
        LinearLayout ok=(LinearLayout)progress.findViewById(R.id.ok_layout);
        TextView txt=(TextView)progress.findViewById(R.id.cancel_txt);
        cancel.setVisibility(View.GONE);
        prog.setVisibility(View.VISIBLE);
        if(!validate_fields()){
            if(!isNetworkConnected()) {
                progress.show();
                prog.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
                txt.setText("Network Error");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                    }
                }, 800);
            }
            else {
                cancel.setVisibility(View.GONE);
                prog.setVisibility(View.GONE);
                ok.setVisibility(View.VISIBLE);
                TextView txt2=(TextView)progress.findViewById(R.id.ok_txt);
                txt2.setText("Job Added");
                progress.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                    }
                }, 800);

                String id=myRef.push().getKey();
                myRef.child(id).setValue(job);
                setContentView(R.layout.employment_add_job);
            }

        }




    }

    private boolean validate_fields() {
        EditText editText1=(EditText)findViewById(R.id.name_data);
        EditText editText2=(EditText)findViewById(R.id.cost_data);
        EditText editText3=(EditText)findViewById(R.id.quantity_data);
        EditText editText4=(EditText)findViewById(R.id.contact_data);
        EditText editText5=(EditText)findViewById(R.id.desc_data);
        Spinner spinner=(Spinner)findViewById(R.id.type_data);
        String type_data="";
        type_data= String.valueOf(spinner.getSelectedItem());
        //String type_data = spinner.getSelectedItem().toString();
        String name_data=editText1.getText().toString();
        String cost_data=editText2.getText().toString();
        String quantity_data=editText3.getText().toString();
        String contact_data=editText4.getText().toString();
        String desc_data=editText5.getText().toString();
        final Drawable error_icon = getResources().getDrawable(R.drawable.error);
        error_icon.setBounds(0, 0,40,40);
        final Boolean[] erros = {false};

        job.setDepartment(type_data);
        if(TextUtils.isEmpty(name_data)) {
            editText1.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            job.setPost(name_data);
            editText1.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(cost_data)) {
            editText2.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            job.setVacancies(cost_data);
            editText2.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(quantity_data)) {
            editText3.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            job.setSalary(quantity_data);
            editText3.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(contact_data)) {
            editText4.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            job.setContact(contact_data);
            editText4.setCompoundDrawables(null,null,null,null);
        }

        if(TextUtils.isEmpty(desc_data)) {
            editText5.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            job.setDescription(desc_data);
            editText5.setCompoundDrawables(null,null,null,null);
        }


        return erros[0];
    }
}
