package com.javaweb.Basic_concepts.model.request;

import lombok.Data;

@Data
public class AddOrUpdateStudentRequest {
    private Integer id;
    private String name;
    private String gender;
    private String birthday;
    private String hometown;
    private Float avg;
    private String subjectName;
    private String majorName;
    private Integer page;
    private Integer pageSize;
}
