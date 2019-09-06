package com.shivamprajapati.studyhard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    public StudentListAdapter(List<StudentList> studentLists) {
        this.studentLists = studentLists;
    }

    public List<StudentList> studentLists;
    public Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_list_layout,viewGroup,false);
        context=viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
          viewHolder.name.setText(studentLists.get(i).getName());
          viewHolder.branch.setText(studentLists.get(i).getBranch());
          viewHolder.year.setText(studentLists.get(i).getYear());
       // final String key= FirebaseDatabase.getInstance().getReference().child("chatId").push().getKey();
          viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

                  databaseReference.child(studentLists.get(i).getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                          Intent intent=new Intent(context,ChatWithStudent.class);
                          intent.putExtra("studentName",studentLists.get(i).getName());
                          intent.putExtra("studentId",studentLists.get(i).getUid());
                          intent.putExtra("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                          intent.putExtra("notificationKey",studentLists.get(i).getNotificationKey());
                          context.startActivity(intent);

                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });
              }
          });

    }

    @Override
    public int getItemCount() {
        return studentLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,branch,year;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.StudentNameinList);
            branch=(TextView)itemView.findViewById(R.id.StudentBranchInList);
            year=(TextView)itemView.findViewById(R.id.studentYearInList);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.studentListLayout);

        }
    }
}
