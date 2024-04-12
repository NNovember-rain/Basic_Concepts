package com.javaweb.Basic_concepts.repository;

import com.javaweb.Basic_concepts.Entity.SubjectEntity;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;

import java.util.List;

public interface SubjectRepository {
    public SubjectEntity findByName(String s);

    public void addSubject(SubjectEntity subjectEntity);
}
