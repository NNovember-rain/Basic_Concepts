package com.javaweb.Basic_concepts.checkrequire;

import CustomException.FieldRequiredException;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CheckRequire {
    public void checkField(AddOrUpdateStudentRequest addOrUpdateStudentRequest) {
        Field[] fields= AddOrUpdateStudentRequest.class.getDeclaredFields();
        for (Field item:fields) {
            item.setAccessible(true);
            String fieldName = item.getName();
            Object value=new Object();
            try {
                value = item.get(addOrUpdateStudentRequest);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(value==null && !fieldName.equals("id")&& !fieldName.equals("page")&& !fieldName.equals("pageSize")) {
                throw new FieldRequiredException("You must fill out the field completely to add or update students");
            }
        }
    }
}
