package com.javaweb.Basic_concepts.repository;

import com.javaweb.Basic_concepts.Entity.StudentEntity;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    public List<StudentEntity> findAll(Map<String, Object> params, List<String> subjectClass);
}
