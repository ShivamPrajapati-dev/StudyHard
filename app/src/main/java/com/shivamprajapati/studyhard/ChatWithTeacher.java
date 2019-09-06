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
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatWithTeacher extends AppCompatActivity {

    String teacherName,teacherId,notificationKey,uid,msg,from,to,myMsg,f,t,creatorName,cn;
    DatabaseReference databaseReference;
    List<ChatWithTeacherList> chatWithTeacherLists;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_teacher);
        chatWithTeacherLists=new ArrayList<>();
        Intent intent=getIntent();
         teacherName=intent.getStringExtra("teacherName");
        teacherId=intent.getStringExtra("teacherId");
         uid=intent.getStringExtra("uid");
         notificationKey=intent.getStringExtra("notificationKey");
        TextView textView=(TextView)findViewById(R.id.chatWithTeacherName);
        recyclerView=(RecyclerView)findViewById(R.id.chatWithTeacherRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        textView.setText(teacherName);

        final EditText message=(EditText)findViewById(R.id.ask);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("chat").child(uid+"with"+teacherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                   if(dataSnapshot.child("fromTeacher").exists()&&dataSnapshot.child("fromTeacher").getValue().toString().equals(teacherId)) {
                       try {
                           msg = dataSnapshot.child("message").getValue().toString();
                           from = dataSnapshot.child("fromTeacher").getValue().toString();
                           to = dataSnapshot.child("token").getValue().toString();
                           creatorName = dataSnapshot.child("creatorName").getValue().toString();
                       } catch (Exception e) {
                       }


                       ChatWithTeacherList chatWithTeacherList = new ChatWithTeacherList(msg, from, to, creatorName);
                       chatWithTeacherLists.add(chatWithTeacherList);
                       ChatWithTeacherListAdapter chatWithTeacherListAdapter = new ChatWithTeacherListAdapter(chatWithTeacherLists);
                       recyclerView.setAdapter(chatWithTeacherListAdapter);
                       recyclerView.scrollToPosition(chatWithTeacherLists.size() - 1);
                   }
                   else if(dataSnapshot.child("fromStudent").exists()&&dataSnapshot.child("fromStudent").getValue().toString().equals(uid)){
                        myMsg = dataSnapshot.child("message").getValue().toString();
                        f = dataSnapshot.child("fromStudent").getValue().toString();
                        t = dataSnapshot.child("token").getValue().toString();
                        cn = dataSnapshot.child("creatorName").getValue().toString();

                        ChatWithTeacherList chatWithTeacherList = new ChatWithTeacherList(myMsg, f, t, cn);
                        chatWithTeacherLists.add(chatWithTeacherList);
                        ChatWithTeacherListAdapter chatWithTeacherListAdapter = new ChatWithTeacherListAdapter(chatWithTeacherLists);
                        recyclerView.setAdapter(chatWithTeacherListAdapter);
                        recyclerView.scrollToPosition(chatWithTeacherLists.size() - 1);


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

       /* databaseReference.child("chat").child(teacherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                     if(dataSnapshot.child("fromStudent").getValue().toString().equals(uid)){
                        myMsg = dataSnapshot.child("message").getValue().toString();
                        f = dataSnapshot.child("fromStudent").getValue().toString();
                        t = dataSnapshot.child("token").getValue().toString();
                        cn = dataSnapshot.child("creatorName").getValue().toString();

                        ChatWithTeacherList chatWithTeacherList = new ChatWithTeacherList(myMsg, f, t, cn);
                        chatWithTeacherLists.add(chatWithTeacherList);
                        ChatWithTeacherListAdapter chatWithTeacherListAdapter = new ChatWithTeacherListAdapter(chatWithTeacherLists);
                        recyclerView.setAdapter(chatWithTeacherListAdapter);
                        recyclerView.scrollToPosition(chatWithTeacherLists.size() - 1);


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


        Button send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=message.getText().toString();
                if(!TextUtils.isEmpty(m)) {

                    String mId = FirebaseDatabase.getInstance().getReference().child("mId").push().getKey();

                    databaseReference.child("chat").child(uid+"with"+teacherId).push();
                    Map messageMap = new HashMap<>();
                    messageMap.put("fromStudent", uid);
                    messageMap.put("message",m);
                    messageMap.put("token",notificationKey);
                    messageMap.put("creatorName", SharedPref.readSharedSettingsUserName(ChatWithTeacher.this, "name", ""));
                   // Toast.makeText(ChatWithTeacher.this,notificationKey,Toast.LENGTH_LONG).show();;
                    databaseReference.child("chat").child(uid+"with"+teacherId).child(mId).updateChildren(messageMap);
                }
                 message.setText(null);

            }
        });




    }



}
