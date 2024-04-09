package com.javaweb.Basic_concepts.utils;

public class StringUtil {
    public static boolean checkString(String data) {
        if(data!=null && !data.trim().equals("")) {
            return true;
        }
        return false;
    }
}
