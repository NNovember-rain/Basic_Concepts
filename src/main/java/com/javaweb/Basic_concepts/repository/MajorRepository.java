package com.javaweb.Basic_concepts.repository;

import com.javaweb.Basic_concepts.Entity.MajorEntity;
import com.javaweb.Basic_concepts.Entity.SubjectEntity;

public interface MajorRepository {
    public MajorEntity findByName(String s);

    public void addMajor(MajorEntity majorEntity);
}
