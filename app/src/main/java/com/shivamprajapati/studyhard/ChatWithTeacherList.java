package com.shivamprajapati.studyhard;

public class ChatWithTeacherList {
    String message;
    String from,to,creatorName;

    public ChatWithTeacherList(String message, String from,String to,String creatorName) {
        this.message = message;
        this.from = from;
        this.to=to;
        this.creatorName=creatorName;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }
    public String getTo(){
        return to;
    }

    public String getCreatorName() {
        return creatorName;
    }
}
