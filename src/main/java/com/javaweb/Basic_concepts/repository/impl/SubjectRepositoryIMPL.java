package com.javaweb.Basic_concepts.repository.impl;


import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.Entity.SubjectEntity;
import com.javaweb.Basic_concepts.repository.SubjectRepository;
import com.javaweb.Basic_concepts.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SubjectRepositoryIMPL implements SubjectRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SubjectEntity findByName(String s) {
        StringBuilder sql=new StringBuilder("SELECT sj.* FROM subject sj where sj.subject_name= '"+s+"';");
        try {
            Query query=entityManager.createNativeQuery(sql.toString(), SubjectEntity.class);
            return (SubjectEntity) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }

    }
    @Transactional
    @Override
    public void addSubject(SubjectEntity subjectEntity) {
        entityManager.persist(subjectEntity);
    }

}
