package com.javaweb.Basic_concepts.utils;

public class isNumberUtil {
    public static boolean check(Object a){
        try{
            Integer.parseInt(a.toString());
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
