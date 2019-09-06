package com.shivamprajapati.studyhard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatWithStudent extends AppCompatActivity {


    String studentName,studentId,notificationKey,uid,msg,from,to,myMsg,f,t,creatorName,cn;
    DatabaseReference databaseReference;
    List<ChatWithStudentList> chatWithStudentLists;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_student);
        chatWithStudentLists=new ArrayList<>();




        Intent intent=getIntent();
        studentName=intent.getStringExtra("studentName");
        studentId=intent.getStringExtra("studentId");
        uid=intent.getStringExtra("uid");
        notificationKey=intent.getStringExtra("notificationKey");
        TextView textView=(TextView)findViewById(R.id.chatWithStudentName);
        textView.setText(studentName);
        uid=intent.getStringExtra("uid");


        recyclerView=(RecyclerView)findViewById(R.id.chatWithStudentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final EditText message=(EditText)findViewById(R.id.tell);
        Button send=(Button)findViewById(R.id.sendAnswer);

        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("chat").child(studentId+"with"+uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

              if(dataSnapshot.exists()){
                  if(dataSnapshot.child("fromStudent").exists()&&dataSnapshot.child("fromStudent").getValue().toString().equals(studentId)) {
                      try {
                          msg = dataSnapshot.child("message").getValue().toString();
                          from = dataSnapshot.child("fromStudent").getValue().toString();
                          to = dataSnapshot.child("token").getValue().toString();
                          creatorName = dataSnapshot.child("creatorName").getValue().toString();
                      } catch (Exception e) {
                      }

                      ChatWithStudentList chatWithStudentList = new ChatWithStudentList(msg, from, to, creatorName);
                      chatWithStudentLists.add(chatWithStudentList);
                      ChatWithStudentListAdapter chatWithStudentListAdapter = new ChatWithStudentListAdapter(chatWithStudentLists);
                      recyclerView.setAdapter(chatWithStudentListAdapter);
                      recyclerView.scrollToPosition(chatWithStudentLists.size() - 1);
                  }
                  else if (dataSnapshot.child("fromTeacher").exists()&&dataSnapshot.child("fromTeacher").getValue().toString().equals(uid)) {
                          try {
                              myMsg = dataSnapshot.child("message").getValue().toString();
                              f = dataSnapshot.child("fromTeacher").getValue().toString();
                              t = dataSnapshot.child("token").getValue().toString();
                              cn = dataSnapshot.child("creatorName").getValue().toString();
                          } catch (Exception e) {
                          }

                          ChatWithStudentList chatWithStudentList = new ChatWithStudentList(myMsg, f, t, cn);
                          chatWithStudentLists.add(chatWithStudentList);
                          ChatWithStudentListAdapter chatWithStudentListAdapter = new ChatWithStudentListAdapter(chatWithStudentLists);

                          recyclerView.setAdapter(chatWithStudentListAdapter);
                          recyclerView.scrollToPosition(chatWithStudentLists.size() - 1);



                  }


            }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* databaseReference.child("chat").child(studentId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.child("fromTeacher").getValue().toString().equals(uid)) {
                        try {
                            myMsg = dataSnapshot.child("message").getValue().toString();
                            f = dataSnapshot.child("fromTeacher").getValue().toString();
                            t = dataSnapshot.child("token").getValue().toString();
                            cn = dataSnapshot.child("creatorName").getValue().toString();
                        } catch (Exception e) {
                        }

                        ChatWithStudentList chatWithStudentList = new ChatWithStudentList(myMsg, f, t, cn);
                        chatWithStudentLists.add(chatWithStudentList);
                        ChatWithStudentListAdapter chatWithStudentListAdapter = new ChatWithStudentListAdapter(chatWithStudentLists);

                        recyclerView.setAdapter(chatWithStudentListAdapter);
                        recyclerView.scrollToPosition(chatWithStudentLists.size() - 1);


                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=message.getText().toString();
                if(!TextUtils.isEmpty(m)) {

                    String mId = FirebaseDatabase.getInstance().getReference().child("mId").push().getKey();
                    databaseReference.child("chat").child(studentId+"with"+uid).push();

                    Map messageMap = new HashMap<>();
                    messageMap.put("fromTeacher", uid);
                    messageMap.put("message", m);
                    messageMap.put("token", notificationKey);
                    messageMap.put("creatorName", SharedPref.readSharedSettingsUserName(ChatWithStudent.this, "name", ""));
                  //  Toast.makeText(ChatWithStudent.this,notificationKey,Toast.LENGTH_LONG).show();
                    databaseReference.child("chat").child(studentId+"with"+uid).child(mId).updateChildren(messageMap);
                }
                message.setText(null);

            }
        });


    }
}
