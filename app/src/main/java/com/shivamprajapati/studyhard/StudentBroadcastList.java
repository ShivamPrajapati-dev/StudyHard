package com.shivamprajapati.studyhard;

public class StudentBroadcastList {
    String creatorName,broadcastMessage,cid;

    public StudentBroadcastList(String creatorName, String broadcastMessage,String cid) {
        this.creatorName = creatorName;
        this.broadcastMessage = broadcastMessage;
        this.cid=cid;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getBroadcastMessage() {
        return broadcastMessage;
    }

    public String getCid() {
        return cid;
    }
}
