package com.shivamprajapati.studyhard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class StudentWorldPagerAdapter extends FragmentStatePagerAdapter {

    int noOfTab;
    public StudentWorldPagerAdapter(FragmentManager fm,int noOfTab) {
        super(fm);
        this.noOfTab=noOfTab;
    }

    @Override
    public Fragment getItem(int i) {

        switch(i) {
            case 0:
               StudentChatTab1 studentChatTab1 = new StudentChatTab1();
               return studentChatTab1;
            case 1:
                StudentTeacherTab2 studentTeacherTab2=new StudentTeacherTab2();
                return studentTeacherTab2;
            case 2:
                StudentProfileTab3 studentProfileTab3=new StudentProfileTab3();
                return studentProfileTab3;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTab;
    }
}
