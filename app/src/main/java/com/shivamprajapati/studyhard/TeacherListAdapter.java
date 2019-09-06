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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {


    public List<TeacherList> teacherLists;
    public Context context;


    public TeacherListAdapter(List<TeacherList> teacherLists){
        this.teacherLists=teacherLists;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teacher_list_layout,viewGroup,false);
       context=viewGroup.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
             viewHolder.teacherName.setText(teacherLists.get(i).getName());
             viewHolder.teacherDep.setText(teacherLists.get(i).getDepartment());

             viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                   //  key= FirebaseDatabase.getInstance().getReference().child("chatId").push().getKey();
                     final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

                     databaseReference.child(teacherLists.get(i).getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                 Intent intent=new Intent(context,ChatWithTeacher.class);
                                 intent.putExtra("teacherName",teacherLists.get(i).getName());
                                 intent.putExtra("teacherId",teacherLists.get(i).getUid());
                                 intent.putExtra("notificationKey",teacherLists.get(i).getNotificationKey());
                               //  Toast.makeText(context,teacherLists.get(i).getNotificationKey(),Toast.LENGTH_LONG).show();
                                 intent.putExtra("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                                 context.startActivity(intent);


                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

                    // Toast.makeText(context,c1,Toast.LENGTH_LONG).show();
                     //Toast.makeText(context,c2,Toast.LENGTH_LONG).show();


                 }
             });

    }


    @Override
    public int getItemCount() {
        return teacherLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView teacherName,teacherDep;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherName=(TextView)itemView.findViewById(R.id.teacherNameInList);
            teacherDep=(TextView)itemView.findViewById(R.id.teacherDepartmentInList);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.teacherListLayout);





        }
    }
}
