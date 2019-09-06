package com.shivamprajapati.studyhard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentBroadcastListAdapter extends RecyclerView.Adapter<StudentBroadcastListAdapter.ViewHolder> {

    List<StudentBroadcastList> studentBroadcastLists;
    Context context;

    public StudentBroadcastListAdapter(List<StudentBroadcastList> studentBroadcastLists) {
        this.studentBroadcastLists = studentBroadcastLists;
    }

    @NonNull
    @Override
    public StudentBroadcastListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.student_broadcast_list,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentBroadcastListAdapter.ViewHolder viewHolder, final int i) {
           viewHolder.creatorName.setText(studentBroadcastLists.get(i).getCreatorName());
           viewHolder.broadcastmsg.setText(studentBroadcastLists.get(i).getBroadcastMessage());

        StorageReference storageReference= FirebaseStorage.getInstance().getReference();
        storageReference.child(studentBroadcastLists.get(i).getCid()).child("profilePicture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
              Picasso.with(context).load(uri).placeholder(R.drawable.profilepic).into(viewHolder.circleImageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Picasso.with(context).load(R.drawable.profilepic).into(viewHolder.circleImageView);
            }
        });

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(context,ParticularBroadcastInStudentWorld.class);
                   intent.putExtra("createrName",studentBroadcastLists.get(i).getCreatorName());
                   intent.putExtra("broadcastmsg",studentBroadcastLists.get(i).getBroadcastMessage());

                  context.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        return studentBroadcastLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView creatorName,broadcastmsg;
        RelativeLayout relativeLayout;
        CircleImageView circleImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            creatorName=(TextView)itemView.findViewById(R.id.broadCastCreatorName);
            broadcastmsg=(TextView)itemView.findViewById(R.id.broadcastContent);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.studentBroadcastList);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.civ);
        }
    }
}
