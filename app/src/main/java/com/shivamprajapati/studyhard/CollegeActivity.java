package com.shivamprajapati.studyhard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CollegeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener{

    private Spinner spinnerType,collegeSpinner,whichUser;
    private Button button;
    private String c,User,nameOfCollege;
    private String[] collegeType,collegeNames,profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        spinnerType=(Spinner)findViewById(R.id.spinnerType);
        collegeSpinner=(Spinner)findViewById(R.id.collegeSpinner);
        whichUser=(Spinner)findViewById(R.id.whichUser);



        button=(Button)findViewById(R.id.next);

        button.setOnClickListener(this);

      collegeType=new String[]{"IIT","NIT","IIIT"};
      profession=new String[]{"TEACHER","STUDENT"};

      ArrayAdapter<String> prof=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,profession);
      whichUser.setAdapter(prof);



      ArrayAdapter<String> type=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,collegeType);
      spinnerType.setAdapter(type);
      spinnerType.setOnItemSelectedListener(this);

      whichUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              User=(String)parent.getSelectedItem();
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });




    }

    public void openAdditional(){


        if(User.equals("TEACHER")){

            finish();
            Intent i=new Intent(this,TeacherAdditionalInformation.class);
            i.putExtra("nameOfCollege",nameOfCollege);
            startActivity(i);
        }
        if(User.equals("STUDENT")){

            finish();
            Intent intent1=new Intent(this,StudentAdditionalInformation.class);
            intent1.putExtra("nameOfCollege",nameOfCollege);
            startActivity(intent1);
        }
       // Toast.makeText(this,whichUser,Toast.LENGTH_LONG).show();


    }


    @Override
    public void onClick(View v) {
        if(v==button){
            openAdditional();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         c=(String)parent.getItemAtPosition(position);
            if(c.equals("IIT")){

                setIIT();

            }
            else if(c.equals("NIT")){
                setNIT();
            }
           else if(c.equals("IIIT")){
               setIIIT();
            }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


       public void setIIT()
       {

           collegeNames=new String[]{"Indian Institute of Technology (IIT) Gandhi Nagar",
                   "Indian Institute of Technology (IIT) Bhubaneshwar",
                   "Indian Institute of Technology (IIT) Madras, Chennai",
                   "Indian Institute of Technology (IIT) Guwahati",
                   "Indian Institute of Technology (IIT) Indore",
                   "Indian Institute of Technology (IIT) Kanpur",
                   "Indian Institute of Technology (IIT) Jodhpur",
                   "Indian Institute of Technology (IIT) Kharagpur",
                   "Indian Institute of Technology (IIT) Hyderabad",
                   "Indian Institute of Technology (IIT) Bombay",
                   "Indian Institute of Technology (IIT) Patna",
                   "Indian Institute of Technology (IIT) Delhi",
                   "Indian Institute of Technology (IIT) Ropar",
                   "Indian Institute of Technology (IIT) Mandi",
                   "Indian Institute of Technology (IIT) Roorkee",
                   "Indian Institute of Technology (IIT BHU) Varanasi",
                   "Indian Institute of Technology (IIT) Jammu",
                   "Indian Institute of Technology (IIT) Palakkad",
                   "Indian Institute of Technology (IIT) Tirupati",
                   "Indian Institute of Technology (IIT) Goa",
                   "Indian Institute of Technology (IIT) Bhilai",
                   "Indian Institute of Technology (IIT) Dharwad",
                   "Indian Institute of Technology (IIT) Dhanbad"};

           ArrayAdapter<String> collegeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, collegeNames);
           collegeSpinner.setAdapter(collegeAdapter);


           collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   nameOfCollege=parent.getItemAtPosition(position).toString();
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

       }
       public void setNIT()
       {

           collegeNames = new String[]{

                   "Dr. B.R. Ambedkar National Institute of Technology, Jallandhar, Punjab",
                   "Malaviya National Institute of Technology Jaipu, Rajasthan",
                   "Maulana Azad National Institute of Technology, Bhopal",
                   "Motilal Nehru National Institute of Technology, Allahabad, U.P.",
                   "National Institute of Technology, Mizoram",
                   "National Institute of Technology, Warangal, Telangana",
                   "National Institute of Technology, Yupia, Arunachal Pradesh",
                   "National Institute of Technology, Silchar, Assam",
                   "National Institute of Technology, Patna, Bihar",
                   "National Institute of Technology, Calicut, Kerala",
                   "National Institute of Technology, Raipur",
                   "National Institute of Technology, Delhi",
                   "National Institute of Technology, Goa",
                   "National Institute of Technology. Hamirpur, H.P.",
                   "National Institute of Technology, Kurukshetra, Haryana",
                   "National Institute of Technology, Srinagar, J & K",
                   "National Institute of Technology. Jamshedpur, Jharkhand",
                   "National Institute of Technology, Manipur",
                   "National Institute of Technology, Meghalaya",
                   "National Institute of Technology, Nagaland",
                   "National Institute of Technology, Rourkela, Orissa",
                   "National Institute of Technology, Puducherry",
                   "National Institute of Technology, Surathkal, Karnataka",
                   "National Institute of Technology, Sikkim",
                   "National Institute of Technology, Tiruchirapalli, Tamil Nadu",
                   "National Institute of Technology, Agartala, Tripura",
                   "National Institute of Technology, (Garhwal), Uttarkhand",
                   "National Institute of Technology, Durgapur, West Bengal",
                   "Sardar Vallabhbhai National Institute of Technology, Surat, Gujarat",
                   "Visvesvaraya National Institute of Technology, Nagpur, Maharashtra",
                   "National Institute of Technology, Tadepalligudam, AP"};

           ArrayAdapter<String> collegeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, collegeNames);
           collegeSpinner.setAdapter(collegeAdapter);


           collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   nameOfCollege=parent.getItemAtPosition(position).toString();
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

       }

       public void setIIIT()
       {
           collegeNames=new String[]{
                   "IIIT Gwalior",
                   "IIIT Allahabad",
                   "IIIT Jabalpur",
                   "IIIT Kancheepuram",
                   "IIIT Sri City, Chittoor",
                   "IIIT Guwahati",
                   "IIIT Vadodara",
                   "IIIT Kota",
                   "IIIT Srirangam",
                   "IIIT Una",
                   "IIIT Sonepat",
                   "IIIT Kalyani",
                   "IIIT Lucknow",
                   "IIIT Dharwad",
                   "IIIT Kurnool",
                   "IIIT Kottayam",
                   "IIIT Manipur",
                   "IIIT Nagpur",
                   "IIIT Pune",
                   "IIIT Ranchi"
           };
           ArrayAdapter<String> collegeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, collegeNames);
           collegeSpinner.setAdapter(collegeAdapter);


           collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   nameOfCollege=parent.getItemAtPosition(position).toString();
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });
       }


    }

