package com.shivamprajapati.studyhard;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    final static  String fileName="checkLogin";
    public static boolean readSharedSettingsIsLogin(Context context,String settingName,boolean defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(settingName,defaultValue);
    }
    public static String readSharedSettingsWhichUser(Context context,String settingName,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName,defaultValue);
    }
    public static String readSharedSettingsWhichCollege(Context context,String settingName,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName,defaultValue);
    }
    public static String readSharedSettingsWhichYear(Context context,String settingName,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName,defaultValue);
    }
    public static String readSharedSettingsTopic(Context context, String settingName, String defaultValue){

        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName,defaultValue);
    }
    public static String readSharedSettingsUserId(Context context,String settingName,String defaultValue){
     SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
     return sharedPreferences.getString(settingName,defaultValue);
    }

    public static String readSharedSettingsUserName(Context context,String settingName,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName,defaultValue);
    }
    public static void saveSharedSettingsUserName(Context context,String settingName,String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();
    }
    public  static void saveSharedSettingsUserId(Context context,String settingName,String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();

    }
    public static void saveSharedSettingsIsLogin(Context context,String settingName,boolean settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(settingName,settingValue).apply();
    }
    public static void saveSharedSettingsWhichUser(Context context,String settingName,String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();
    }
    public static void saveSharedSettingsWhichYear(Context context,String settingName,String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();
    }

    public static void saveSharedSettingsWhichCollege(Context context, String settingName, String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();
    }
    public static void saveSharedSettingsTopic(Context context,String settingName,String settingValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(settingName,settingValue).apply();
    }
}
