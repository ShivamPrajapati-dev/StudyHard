package com.shivamprajapati.studyhard;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherAdditionalInformation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText teacherName;

    private Spinner branchSelector;
    private Button nextActivity;
    private String name,bs,nameOfCollege;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_additional_information);

        teacherName=(EditText)findViewById(R.id.studentName);
        branchSelector=(Spinner)findViewById(R.id.branchSelectorStudent);
        nextActivity=(Button)findViewById(R.id.nextActivityTeacher);
        Intent intent=getIntent();
        nameOfCollege=intent.getStringExtra("nameOfCollege");

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        String[] branch=new String[]{"Computer Science Engineering","Electronics and Communication Engineering","Electrical Engineering","Mechanical Engineering"
                ,"Information Technology Engineering","Civil Engineering","Chemical Engineering"
                ,
                "Aeronautical Engineering",

                "Agricultural Engineering" ,

                "Mining Engineering" ,

                "Biochemical Engineering" ,

                "Electrical and Instrumentation Engineering" ,

                "Metallurgical Engineering"};




        ArrayAdapter<String> branchAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,branch);
        branchSelector.setAdapter(branchAdapter);

        nextActivity.setOnClickListener(this);
        branchSelector.setOnItemSelectedListener(this);


    }

    public void openTeacher()
    {
        name=teacherName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
            return;
        }
        TeacherInformation teacherInformation=new TeacherInformation(name,bs,"teacher",nameOfCollege);
        SharedPref.saveSharedSettingsWhichCollege(this,"nameOfCollege",nameOfCollege);
        SharedPref.saveSharedSettingsUserName(this,"name",name);
        databaseReference.child(firebaseUser.getUid()).setValue(teacherInformation);
         finish();
        startActivity(new Intent(this,TeacherWorld.class));


    }

    @Override
    public void onClick(View v) {
        if(v==nextActivity)
            openTeacher();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        bs=(String)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
