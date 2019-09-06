package com.shivamprajapati.studyhard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


public class TeacherWorld extends AppCompatActivity implements TeacherChatTab1.OnFragmentInteractionListener,TeacherStudentTab2.OnFragmentInteractionListener,TeacherProfileTab3.OnFragmentInteractionListener{

    private FirebaseAuth firebaseAuth;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_world);

        firebaseAuth=FirebaseAuth.getInstance();

        SharedPref.saveSharedSettingsIsLogin(this,"isLoggedIn",true);
        SharedPref.saveSharedSettingsWhichUser(this,"whichUser","teacher");
      final  DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
      databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("notificationKey").setValue(FirebaseInstanceId.getInstance().getToken());




         tabLayout=(TabLayout)findViewById(R.id.teacherTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("BROADCAST"));
        tabLayout.addTab(tabLayout.newTab().setText("STUDENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("MY PROFILE"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         viewPager=(ViewPager)findViewById(R.id.teacherViewPager);
        TeacherWorldPageAdapter teacherWorldPageAdapter=new TeacherWorldPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(teacherWorldPageAdapter);

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
