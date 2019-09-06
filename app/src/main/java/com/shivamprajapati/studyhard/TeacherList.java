package com.shivamprajapati.studyhard;

public class TeacherList {
    public String mName;


    public String mDepartment,uid,notificationKey;


    public TeacherList(String mName, String mDepartment,String uid,String notificationKey) {
        this.mName = mName;
        this.mDepartment = mDepartment;
        this.uid=uid;
        this.notificationKey=notificationKey;

    }

    public String getName() {
        return mName;
    }

    public String getDepartment() {
        return mDepartment;
    }
    public String getUid(){ return uid;}

    public String getNotificationKey() {
        return notificationKey;
    }
}
