package com.shivamprajapati.studyhard;

public class StudentList {

    public String mName,mBranch,mYear,uid,notificationKey;


    public StudentList(String mName, String mBranch, String mYear,String uid,String notificationKey) {
        this.mName = mName;
        this.mBranch = mBranch;
        this.mYear = mYear;
        this.uid=uid;
        this.notificationKey=notificationKey;
    }
    public String getName() {
        return mName;
    }

    public String getBranch() {
        return mBranch;
    }

    public String getYear() {
        return mYear;
    }

    public String getUid() { return uid;}

    public String getNotificationKey() {
        return notificationKey;
    }
}
