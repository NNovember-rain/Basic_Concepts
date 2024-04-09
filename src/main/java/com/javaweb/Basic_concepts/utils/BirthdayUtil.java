package com.javaweb.Basic_concepts.utils;

public class BirthdayUtil {
    public static String solve(String s){
        String[] tmp=s.trim().split("-");
        return tmp[2]+"-"+tmp[1]+"-"+tmp[0];
    }
}
