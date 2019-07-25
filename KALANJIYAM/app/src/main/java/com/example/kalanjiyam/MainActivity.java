package com.example.kalanjiyam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import com.example.kalanjiyam.dataModel.Authorise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends Activity {
    private Dialog progress;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        progress= new Dialog(MainActivity.this);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.custome_progress_bar);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Boolean check=pref.getBoolean("key_commit", false);
                if (check) {
                    String u_id = pref.getString("u_id", "null");
                    Intent intent = new Intent(getApplicationContext(), Home_page.class);
                    intent.putExtra("u_id", u_id);
                    startActivityForResult(intent, 1111);
                }
                else{
                    setContentView(R.layout.login_page);
                }
            }
        }, 1000);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean login_error(){
        final EditText uid_edt=(EditText)findViewById(R.id.uid_txt);
        final EditText pass_edit=(EditText)findViewById(R.id.pass_txt);
        Boolean erros=false;
        String uid_txt=uid_edt.getText().toString();
        String pass=pass_edit.getText().toString();
        final Drawable error_icon = getResources().getDrawable(R.drawable.error);
        error_icon.setBounds(0, 0,40,40);

        if(TextUtils.isEmpty(uid_txt)){
            uid_edt.setCompoundDrawables(null,null,error_icon,null);
            erros=true;
        }else{
            uid_edt.setCompoundDrawables(null,null,null,null);
        }
        uid_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0){
                    uid_edt.setCompoundDrawables(null,null,null,null);
                }else{
                    uid_edt.setCompoundDrawables(null,null,error_icon,null);
                }
            }
        });

        if(TextUtils.isEmpty(pass)){
            pass_edit.setCompoundDrawables(null,null,error_icon,null);
            erros=true;
        }else{
            pass_edit.setCompoundDrawables(null,null,null,null);
        }
        pass_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0){
                    pass_edit.setCompoundDrawables(null,null,null,null);
                }else{
                    pass_edit.setCompoundDrawables(null,null,error_icon,null);
                }
            }
        });

        if (!erros){
            return true;
        }
        return false;
    }

    public void login_fun(View view){
        final EditText uid_edt=(EditText)findViewById(R.id.uid_txt);
        final EditText pass_edit=(EditText)findViewById(R.id.pass_txt);
        final String pass2=(pass_edit.getText()).toString();
        final String u_id2=(uid_edt.getText()).toString();
        LinearLayout cancel=(LinearLayout)progress.findViewById(R.id.cancel_layout);
        LinearLayout prog=(LinearLayout)progress.findViewById(R.id.progress_layout);
        TextView txt=(TextView)progress.findViewById(R.id.cancel_txt);
        cancel.setVisibility(View.GONE);
        prog.setVisibility(View.VISIBLE);
        if(login_error()){
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
            else {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Query query = reference.child("authorise").orderByChild("u_id").equalTo(u_id2);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Authorise a = new Authorise();
                            for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                                a = Snapshot.getValue(Authorise.class);
                            }
                            String password = a.getPassword();
                            if (password.equals(pass2)) {
                                progress.dismiss();
                                Log.d("ok","ok");

                                editor.putBoolean("key_commit", true);
                                editor.putString("u_id", u_id2);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), Home_page.class);
                                intent.putExtra("u_id",u_id2);
                                startActivityForResult(intent ,1111);
                            }
                            else {
                                Log.d("ok","no");
                                LinearLayout cancel = (LinearLayout) progress.findViewById(R.id.cancel_layout);
                                LinearLayout prog = (LinearLayout) progress.findViewById(R.id.progress_layout);
                                TextView txt = (TextView) progress.findViewById(R.id.cancel_txt);
                                txt.setText("Invalid Password ");
                                prog.setVisibility(View.GONE);
                                cancel.setVisibility(View.VISIBLE);
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progress.dismiss();
                                    }
                                }, 800);

                            }
                        } else {
                            LinearLayout cancel = (LinearLayout) progress.findViewById(R.id.cancel_layout);
                            LinearLayout prog = (LinearLayout) progress.findViewById(R.id.progress_layout);
                            TextView txt = (TextView) progress.findViewById(R.id.cancel_txt);
                            txt.setText("Invalid User id ");
                            prog.setVisibility(View.GONE);
                            cancel.setVisibility(View.VISIBLE);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progress.dismiss();
                                }
                            }, 800);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        LinearLayout cancel = (LinearLayout) progress.findViewById(R.id.cancel_layout);
                        LinearLayout prog = (LinearLayout) progress.findViewById(R.id.progress_layout);
                        prog.setVisibility(View.GONE);
                        cancel.setVisibility(View.VISIBLE);
                        TextView txt = (TextView) progress.findViewById(R.id.cancel_txt);
                        txt.setText("Error Occured ");

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                            }
                        }, 800);
                    }

                });
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode==Activity.RESULT_OK) {
            setContentView(R.layout.login_page);
        }
        else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
