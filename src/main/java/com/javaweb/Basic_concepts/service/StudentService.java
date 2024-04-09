package com.javaweb.Basic_concepts.service;

import com.javaweb.Basic_concepts.model.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<StudentDTO> findAll(Map<String,Object> params, List<String> subjectClass);

}
