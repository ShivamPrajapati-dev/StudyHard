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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogin;
    private String email,password,tag;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser=firebaseAuth.getCurrentUser();

        if(SharedPref.readSharedSettingsIsLogin(this,"isLoggedIn",false)){
            if(SharedPref.readSharedSettingsWhichUser(this,"whichUser","").equals("student")){
               finish();
                startActivity(new Intent(this,StudentWorld.class));
            }
            if(SharedPref.readSharedSettingsWhichUser(this,"whichUser","").equals("teacher")){
                finish();
                startActivity(new Intent(this,TeacherWorld.class));
            }
        }


        editTextEmail = (EditText) findViewById(R.id.loginEmail);
        editTextPassword = (EditText) findViewById(R.id.loginPassword);
        textViewLogin=(TextView)findViewById(R.id.textViewLogin);





        registerButton = (Button) findViewById(R.id.login);

        progressDialog=new ProgressDialog(this);


       registerButton.setOnClickListener(this);

       textViewLogin.setOnClickListener(this);


    }

    public void register(){
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString();





        if(TextUtils.isEmpty(email)) {
        Toast.makeText(MainActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
        return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Registering user....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    finish();
                    Intent intent=new Intent(MainActivity.this,CollegeActivity.class);

                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this,"Unable to register"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v==registerButton){
            register();
        }
        if(v==textViewLogin){
            finish();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }


    }


}



