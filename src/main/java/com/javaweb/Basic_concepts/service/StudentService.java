package com.javaweb.Basic_concepts.service;

import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<StudentDTO> findAll(Map<String,Object> params, List<String> subjectClass);
    public void deleteById(List<Integer> ids);
    public StudentDTO addOrUpdateSt(AddOrUpdateStudentRequest addOrUpdateStudentRequest);
    public List<StudentDTO> getStudentByBirthDay(String s);

}
