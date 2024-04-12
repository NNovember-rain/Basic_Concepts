package com.javaweb.Basic_concepts.repository;

import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.Entity.SubjectEntity;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

public interface StudentRepository {
    public List<StudentEntity> findAll(Map<String, Object> params, List<String> subjectClass);

    public void deleteStudent(StudentEntity studentEntity);

    public  StudentEntity findById(Integer id);

    public void addStudent(StudentEntity studentEntity);

    public void updateStudent(StudentEntity studentEntity);

    public List<StudentEntity> findByDayandMonth(Integer d, Integer m);
}
