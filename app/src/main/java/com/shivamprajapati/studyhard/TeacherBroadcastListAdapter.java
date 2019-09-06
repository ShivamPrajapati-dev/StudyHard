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

import java.util.List;

public class TeacherBroadcastListAdapter extends RecyclerView.Adapter<TeacherBroadcastListAdapter.ViewHolder> {

    List<TeacherBroadcastList> teacherBroadcastLists;
    Context context;

    public TeacherBroadcastListAdapter(List<TeacherBroadcastList> teacherBroadcastLists) {
        this.teacherBroadcastLists = teacherBroadcastLists;
    }

    @NonNull
    @Override
    public TeacherBroadcastListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.teacher_broadcast_list,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherBroadcastListAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.creatorName.setText(teacherBroadcastLists.get(i).getCreatorName());
        viewHolder.broadcastmsg.setText(teacherBroadcastLists.get(i).getBroadcastMessage());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ParticularBroadcastInTeacherWorld.class);
                intent.putExtra("creatorName",teacherBroadcastLists.get(i).getCreatorName());
                intent.putExtra("bcMsg",teacherBroadcastLists.get(i).getBroadcastMessage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherBroadcastLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView creatorName,broadcastmsg;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             relativeLayout=(RelativeLayout)itemView.findViewById(R.id.teacherBroadcastList);
            creatorName=(TextView)itemView.findViewById(R.id.broadCastCreatorNameTeacher);
            broadcastmsg=(TextView)itemView.findViewById(R.id.broadcastContentTeacher);
        }
    }
}
