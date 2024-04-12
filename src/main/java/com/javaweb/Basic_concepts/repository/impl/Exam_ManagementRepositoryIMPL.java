package com.javaweb.Basic_concepts.repository.impl;

import com.javaweb.Basic_concepts.Entity.Exam_ManagementEntity;
import com.javaweb.Basic_concepts.repository.Exam_ManagementRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Exam_ManagementRepositoryIMPL implements Exam_ManagementRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addExamManage(Exam_ManagementEntity examManagementEntity) {
        entityManager.persist(examManagementEntity);
    }
}
