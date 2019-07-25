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

import com.example.kalanjiyam.dataModel.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Admin on 3/3/2019.
 */

public class Get_Job extends AppCompatActivity {
    private Dialog progress;
    public DatabaseReference myRef;
    public Product product;
    String data1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_job);
        Bundle extras = getIntent().getExtras();
        data1 = extras.getString("data1");
        product=new Product();
        progress= new Dialog(Get_Job.this);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.custome_progress_bar);
        myRef = FirebaseDatabase.getInstance().getReference("Job Apply");
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void add_product_funct(View view){
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
                txt2.setText("Job Applied");
                progress.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                        finish();
                    }
                }, 800);

                String id=myRef.push().getKey();
                myRef.child(id).setValue(product);

            }

        }




    }

    private boolean validate_fields() {
        EditText editText1=(EditText)findViewById(R.id.name_data);
        EditText editText2=(EditText)findViewById(R.id.cost_data);
        EditText editText3=(EditText)findViewById(R.id.quantity_data);
        EditText editText4=(EditText)findViewById(R.id.contact_data);
        EditText editText5=(EditText)findViewById(R.id.desc_data);



        //String type_data = spinner.getSelectedItem().toString();
        String name_data=editText1.getText().toString();
        String cost_data=editText2.getText().toString();
        String quantity_data=editText3.getText().toString();
        String contact_data=editText4.getText().toString();
        String desc_data=editText5.getText().toString();
        final Drawable error_icon = getResources().getDrawable(R.drawable.error);
        error_icon.setBounds(0, 0,40,40);
        final Boolean[] erros = {false};

        product.setType(data1);
        if(TextUtils.isEmpty(name_data)) {
            editText1.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            product.setName(name_data);
            editText1.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(cost_data)) {
            editText2.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            product.setCost(cost_data);
            editText2.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(quantity_data)) {
            editText3.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            product.setQuality(quantity_data);
            editText3.setCompoundDrawables(null,null,null,null);
        }
        if(TextUtils.isEmpty(contact_data)) {
            editText4.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            product.setContact(contact_data);
            editText4.setCompoundDrawables(null,null,null,null);
        }

        if(TextUtils.isEmpty(desc_data)) {
            editText5.setCompoundDrawables(null, null, error_icon, null);
            erros[0] =true;
        }
        else{
            product.setDescription(desc_data);
            editText5.setCompoundDrawables(null,null,null,null);
        }


        return erros[0];
    }
}
