package com.example.kalanjiyam;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kalanjiyam.adapter.Products_List_Adapter;
import com.example.kalanjiyam.dataModel.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 2/28/2019.
 */

public class Farming_Buy_Product extends AppCompatActivity {
    private ArrayList<Product> P_array = new ArrayList<Product>();
    private int count=0;
    private ValueEventListener listener;
    private Dialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farming_buy_product);
        progress= new Dialog(Farming_Buy_Product.this);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.custome_progress_bar);
        LinearLayout cancel=(LinearLayout)progress.findViewById(R.id.cancel_layout);
        LinearLayout prog=(LinearLayout)progress.findViewById(R.id.progress_layout);
        TextView txt=(TextView)progress.findViewById(R.id.cancel_txt);
        progress.show();
        if(!isNetworkConnected()){
            prog.setVisibility(View.GONE);
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

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Farm_products");
        final Products_List_Adapter adapter = new Products_List_Adapter(Farming_Buy_Product.this, P_array,1);
        final ListView listView = (ListView) findViewById(R.id.product_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("res", "res");
                            Product par = new Product();
                            par = P_array.get(position);
                            Intent intent = new Intent(getApplicationContext(), Farming_View_Product.class);
                            intent.putExtra("obj", (Serializable) par);
                            startActivityForResult(intent ,1111);


//                            final Dialog dialog = new Dialog(ListView_participants.this);
//                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                            dialog.setContentView(R.layout.custome_backpress_dilalog);
//                            final Button dialog_yes = (Button) dialog.findViewById(R.id.dialog_yes);
//                            final Button dialog_no = (Button) dialog.findViewById(R.id.dialog_no);
//                            dialog_yes.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    String id = par.getId();
//                                    myRef.child(id).removeValue();
//                                    P_array.remove(position);
//                                    adapter.notifyDataSetChanged();
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog_no.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog.show();

                        }
            });


        if (count == 0) {
            listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                        Product p = Snapshot.getValue(Product.class);
                        P_array.add(p);
                        adapter.notifyDataSetChanged();
                        progress.dismiss();
                    }
                    myRef.removeEventListener(listener);
                    if (!dataSnapshot.exists()) {
                        progress.dismiss();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            myRef.addValueEventListener(listener);
            count++;
        }


        listView.setAdapter(adapter);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
