package com.javaweb.Basic_concepts.repository;

import com.javaweb.Basic_concepts.Entity.Exam_ManagementEntity;
import org.springframework.stereotype.Component;

public interface Exam_ManagementRepository {
    public void addExamManage(Exam_ManagementEntity examManagementEntity);
}
