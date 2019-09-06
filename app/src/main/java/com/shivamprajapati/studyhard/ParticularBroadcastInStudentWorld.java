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

public class ParticularBroadcastInStudentWorld extends AppCompatActivity {

    TextView bcMsgBody,bcMsgSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_broadcast_in_student_world);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarStudent);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        bcMsgBody=(TextView)findViewById(R.id.bcMsgBody);
        bcMsgSubject=(TextView)findViewById(R.id.bcMsgSubject);
        bcMsgBody.setText(intent.getStringExtra("broadcastmsg"));
        bcMsgSubject.setText("Subject: "+"000000000");

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.CollapsingToolbarStudent);
        collapsingToolbarLayout.setTitle(intent.getStringExtra("createrName"));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabStudentWorld);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
