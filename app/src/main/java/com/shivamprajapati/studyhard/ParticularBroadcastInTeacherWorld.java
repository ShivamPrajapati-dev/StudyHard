package com.shivamprajapati.studyhard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ParticularBroadcastInTeacherWorld extends AppCompatActivity {

    TextView bcMsgBody,bcMsgSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_broadcast_in_teacher_world);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTeacher);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.CollapsingToolbarTeacher);
        collapsingToolbarLayout.setTitle(intent.getStringExtra("creatorName"));

        bcMsgBody=(TextView)findViewById(R.id.bcMsgBodyTeacher);
        bcMsgSubject=(TextView)findViewById(R.id.bcMsgSubjectTeacher);
        bcMsgBody.setText(intent.getStringExtra("bcMsg"));
        bcMsgSubject.setText("Subject: "+"000000");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTeacherWorld);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
