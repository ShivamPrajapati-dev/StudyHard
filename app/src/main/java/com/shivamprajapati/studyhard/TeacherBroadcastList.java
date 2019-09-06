package com.shivamprajapati.studyhard;

public class TeacherBroadcastList {
    String creatorName,broadcastMessage;

    public TeacherBroadcastList(String creatorName, String broadcastMessage) {
        this.creatorName = creatorName;
        this.broadcastMessage = broadcastMessage;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getBroadcastMessage() {
        return broadcastMessage;
    }
}
