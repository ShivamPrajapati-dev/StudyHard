package com.shivamprajapati.studyhard;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


public class StudentAdditionalInformation extends AppCompatActivity implements View.OnClickListener{
    private EditText studentName;
    private EditText studentId;
    private Spinner yearSelector;
    private Spinner branchSelector;
    private Button nextActivity;
     String name,id,y,b,nameOfCollege;
    private DatabaseReference databaseReference;
  //  private StudentInformation studentInformation;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_additional_information);

        studentName=(EditText)findViewById(R.id.studentName);
        studentId=(EditText)findViewById(R.id.studentId);
        yearSelector=(Spinner)findViewById(R.id.branchSelectorStudent);
        branchSelector=(Spinner)findViewById(R.id.branchSelector);
        nextActivity=(Button)findViewById(R.id.nextActivity);


        firebaseAuth=FirebaseAuth.getInstance();
         Intent intent=getIntent();
         nameOfCollege=intent.getStringExtra("nameOfCollege");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser= firebaseAuth.getCurrentUser();

        Toast.makeText(this,firebaseUser.getEmail().toString(),Toast.LENGTH_LONG).show();
        nextActivity.setOnClickListener(this);

        String[] year=new String[]{"1st Year","2nd Year","3rd Year","4th Year"};
        final String[] branch=new String[]{"Computer Science Engineering","Electronics and Communication Engineering","Electrical Engineering","Mechanical Engineering"
                                         ,"Information Technology Engineering","Civil Engineering","Chemical Engineering"
                ,
                "Aeronautical Engineering",

                "Agricultural Engineering" ,

                "Mining Engineering" ,

                "Biochemical Engineering" ,

                "Electrical and Instrumentation Engineering" ,

                "Metallurgical Engineering"};


        ArrayAdapter<String> yearAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,year);
        yearSelector.setAdapter(yearAdapter);

        ArrayAdapter<String> branchAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,branch);
        branchSelector.setAdapter(branchAdapter);

        yearSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y=(String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        branchSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b=(String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private void openStudent() {
        name=studentName.getText().toString().trim();
        id=studentId.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(id)){
            Toast.makeText(this,"Please enter your id",Toast.LENGTH_SHORT).show();
            return;
        }

       // Toast.makeText(this,y+" "+b,Toast.LENGTH_LONG).show();
      StudentInformation studentInformation=new StudentInformation(name,id,y,b,"student",nameOfCollege);
      SharedPref.saveSharedSettingsWhichCollege(this,"nameOfCollege",nameOfCollege);
      SharedPref.saveSharedSettingsUserId(this,"userId",firebaseUser.getUid());
      SharedPref.saveSharedSettingsUserName(this,"name",name);
      //Toast.makeText(this,y,Toast.LENGTH_SHORT).show();
      SharedPref.saveSharedSettingsWhichYear(this,"year",y);

        FirebaseMessaging.getInstance().subscribeToTopic(nameOfCollege.replaceAll("\\s+","")+y.replaceAll("\\s+","")).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

       //String tId=databaseReference.child("service").child(nameOfCollege).child(nameOfCollege+y).push().getKey();
      // Toast.makeText(this,tId,Toast.LENGTH_LONG).show();
      //databaseReference.child("service").child(nameOfCollege).child(nameOfCollege+y).child(tId).child("token").setValue(FirebaseInstanceId.getInstance().getToken());







       databaseReference.child(firebaseUser.getUid()).setValue(studentInformation);

         finish();
       startActivity(new Intent(this,StudentWorld.class));



    }

    @Override
    public void onClick(View v) {
        if(v==nextActivity){
            openStudent();

        }

    }


}
