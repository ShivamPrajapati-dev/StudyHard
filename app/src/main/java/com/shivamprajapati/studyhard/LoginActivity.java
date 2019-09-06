package com.shivamprajapati.studyhard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    private DatabaseReference databaseReference;
    private TextView textViewSignin;
    String email,password,tag;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    SharedPreferences sharedPreferences;
    boolean isLoggedIn;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail=(EditText)findViewById(R.id.loginEmail);
        editTextPassword=(EditText)findViewById(R.id.loginPassword);
        loginButton=(Button)findViewById(R.id.login);

        textViewSignin=(TextView)findViewById(R.id.textViewLogin);
        progressDialog=new ProgressDialog(this);



        loginButton.setOnClickListener(this);

        textViewSignin.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();



    }

    public void guessActivity(){


        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    tag = dataSnapshot.child("tag").getValue().toString();


                    if (tag.equals("student")) {
                        finish();
                        startActivity(new Intent(LoginActivity.this, StudentWorld.class));
                    }
                    if (tag.equals("teacher")) {
                        finish();
                        startActivity(new Intent(LoginActivity.this, TeacherWorld.class));
                    }

                } else {
                    finish();
                    startActivity(new Intent(LoginActivity.this, CollegeActivity.class));
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void userLogin()
    {

        email=editTextEmail.getText().toString().trim();
        password=editTextPassword.getText().toString();


        if(TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loging in....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();

                    guessActivity();



                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login failed,try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==loginButton)
            userLogin();
        if(v==textViewSignin)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

    }


}
