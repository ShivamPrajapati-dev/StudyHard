package com.shivamprajapati.studyhard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TeacherWorldPageAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;
    public TeacherWorldPageAdapter(FragmentManager fm,int noOfTabs) {
        super(fm);
        this.noOfTabs=noOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                TeacherChatTab1 teacherChatTab1=new TeacherChatTab1();
                return teacherChatTab1;
            case 1:
                TeacherStudentTab2 teacherStudentTab2=new TeacherStudentTab2();
                return teacherStudentTab2;
            case 2:
                TeacherProfileTab3 teacherProfileTab3=new TeacherProfileTab3();
                return teacherProfileTab3;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
