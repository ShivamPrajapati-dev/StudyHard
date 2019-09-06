package com.shivamprajapati.studyhard;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


import org.json.JSONException;
import org.json.JSONObject;

public class StudentWorld extends AppCompatActivity implements StudentChatTab1.OnFragmentInteractionListener,StudentTeacherTab2.OnFragmentInteractionListener,StudentProfileTab3.OnFragmentInteractionListener {
    private FirebaseAuth firebaseAuth;
   // private Button button;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_world);

       // button=(Button)findViewById(R.id.signout);
        firebaseAuth=FirebaseAuth.getInstance();
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        final FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        SharedPref.saveSharedSettingsIsLogin(this,"isLoggedIn",true);
        SharedPref.saveSharedSettingsWhichUser(this,"whichUser","student");


        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("notificationKey").setValue(FirebaseInstanceId.getInstance().getToken());


        tabLayout=(TabLayout)findViewById(R.id.studentTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("BROADCAST"));
        tabLayout.addTab(tabLayout.newTab().setText("TEACHERS"));
        tabLayout.addTab(tabLayout.newTab().setText("MY PROFILE"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager=(ViewPager)findViewById(R.id.studentViewPager);
        StudentWorldPagerAdapter studentWorldPagerAdapter=new StudentWorldPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(studentWorldPagerAdapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
