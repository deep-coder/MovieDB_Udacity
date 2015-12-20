package com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jdeepak on 12/20/2015.
 */
public class AssortedUtils {

    public static void SavePreferences(String key,String value,Context context){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = SP.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getPreferences(String key,Context context){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        return SP.getString(key, null);
    }


}
