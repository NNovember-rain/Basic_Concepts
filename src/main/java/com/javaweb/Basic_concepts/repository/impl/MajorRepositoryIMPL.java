package com.javaweb.Basic_concepts.repository.impl;

import com.javaweb.Basic_concepts.Entity.MajorEntity;
import com.javaweb.Basic_concepts.repository.MajorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MajorRepositoryIMPL implements MajorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MajorEntity findByName(String s) {
        StringBuilder sql=new StringBuilder("SELECT m.* FROM major m WHERE m.major_name = '"+s+"' ;");
        try {
            Query query = entityManager.createNativeQuery(sql.toString(), MajorEntity.class);
            return (MajorEntity) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }

    @Transactional
    @Override
    public void addMajor(MajorEntity majorEntity) {
        entityManager.persist(majorEntity);
    }
}
