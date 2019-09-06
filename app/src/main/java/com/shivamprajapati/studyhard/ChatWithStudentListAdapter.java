package com.shivamprajapati.studyhard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatWithStudentListAdapter extends RecyclerView.Adapter<ChatWithStudentListAdapter.ViewHolder> {

    List<ChatWithStudentList> chatWithStudentLists;

    public Context context;

    public ChatWithStudentListAdapter(List<ChatWithStudentList> chatWithStudentLists) {
        this.chatWithStudentLists = chatWithStudentLists;
    }


    @NonNull
    @Override
    public ChatWithStudentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context=viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.chat_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatWithStudentListAdapter.ViewHolder viewHolder, int i) {

        if(chatWithStudentLists.get(i).getFrom().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            viewHolder.rightmsg.setText(chatWithStudentLists.get(i).getMessage());
            viewHolder.leftmsg.setVisibility(View.INVISIBLE);
        }
        else
        {
            viewHolder.leftmsg.setText(chatWithStudentLists.get(i).getMessage());
            viewHolder.rightmsg.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return chatWithStudentLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView leftmsg,rightmsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            leftmsg=(TextView)itemView.findViewById(R.id.LeftMessageId);
            rightmsg=(TextView)itemView.findViewById(R.id.RightMessageId);

        }
    }
}
