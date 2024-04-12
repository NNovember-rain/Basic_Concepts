package com.javaweb.Basic_concepts.repository.impl;


import CustomException.FieldRequiredException;
import CustomException.IdExistException;
import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.repository.StudentRepository;
import com.javaweb.Basic_concepts.utils.BirthdayUtil;
import com.javaweb.Basic_concepts.utils.StringUtil;
import com.javaweb.Basic_concepts.utils.isNumberUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Transactional
@Repository
public class StudentRepositoryIMPL implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void checkJoinTable(Map<String, Object> params, List<String> subjectClass, StringBuilder sql){
        if(params.containsKey("avgPonit") || subjectClass!=null){
            sql.append("INNER JOIN exam_management em ON s.id=em.student_id");
            sql.append(" INNER JOIN subject sj ON em.subject_id=sj.id ");
        }
        if(params.containsKey("major_Name") || subjectClass!=null){
            sql.append("INNER JOIN major mj ON s.major_id=mj.id");
        }
    }
    public void checkQuerryNormal(Map<String, Object> params, List<String> subjectClass,StringBuilder where){
        for(String it:params.keySet()){
            if( !it.equals("subjectClass") &&  !it.startsWith("avg") &&  !it.startsWith("birthday") &&!it.equals("major_Name")){
                if(!isNumberUtil.check (params.get(it)) && StringUtil.checkString(params.get(it).toString())){
                    where.append(" AND s."+it+" LIKE '%"+params.get(it)+"%' ");
                }
            }
        }
    }

    public void checkQuerrySpecial(Map<String, Object> params, List<String> subjectClass,StringBuilder where){
        for(String it:params.keySet()){
            if( it.equals("avgPointFrom") && StringUtil.checkString(params.get(it).toString())){
                where.append(" AND s.avg"+" >= "+params.get(it)+" ");
            }
            if( it.equals("avgPointTo") && StringUtil.checkString(params.get(it).toString())){
                where.append(" AND s.avg" + " <= "+params.get(it)+" ");
            }

            if( it.equals("birthdayFrom") && StringUtil.checkString(params.get(it).toString())){
                String birthdayFrom= BirthdayUtil.solve(params.get(it).toString());
                where.append(" AND s.birthday"+" >= '"+birthdayFrom+"' ");
            }
            if( it.equals("birthdayTo") && StringUtil.checkString(params.get(it).toString())){
                String birthdayTo= BirthdayUtil.solve(params.get(it).toString());
                where.append(" AND s.birthday" +" <= '"+birthdayTo+"' ");
            }
            if( it.equals("major_Name") && StringUtil.checkString(params.get(it).toString())){
                where.append(" AND mj.major_Name"+ " LIKE '%"+params.get(it)+"%' ");
            }
        }

        if(subjectClass!=null){
            List<String> subjects=new ArrayList<>();
            for(String s:subjectClass){
                subjects.add("'"+s+"'");
            }
            for(String s:subjectClass){

            }
            where.append(" AND sj.subject_name IN (" +String.join(",",subjects )+") ");
        }
    }

    public void checkPage(Map<String, Object> params,StringBuilder where){
        Integer page,pageSize;
        try {
            page = Integer.parseInt(params.get("page").toString());
            pageSize = Integer.parseInt(params.get("pageSize").toString());
        }catch (Exception e){
            page=null;
            pageSize=null;
        }
        if(page!=null && pageSize!=null) {
            if (page == 1) {
                where.append(" LIMIT " + pageSize +" ");
            } else where.append(" LIMIT " + pageSize + " OFFSET " + (page * pageSize-pageSize) + " ");
        }
    }
    public  List<StudentEntity> solveResult(StringBuilder sql) {
        Query query=entityManager.createNativeQuery(sql.toString(), StudentEntity.class);
        return query.getResultList();
    }
    @Override
    public List<StudentEntity> findAll(Map<String, Object> params, List<String> subjectClass) {
        StringBuilder sql=new StringBuilder("SELECT s.* FROM student s ");

        StringBuilder where=new StringBuilder(" WHERE 1=1 ");
        checkJoinTable(params,subjectClass,sql);
        checkQuerryNormal(params,subjectClass,where);
        checkQuerrySpecial(params,subjectClass,where);
        where.append(" GROUP BY s.id ");
        checkPage(params,where);
        sql.append(where.toString());
        return solveResult(sql);
    }

    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        try {
            entityManager.remove(studentEntity);
        }catch (Exception e){
            throw new IdExistException("StudentId is not exits !");
        }
    }



    @Override
    public StudentEntity findById(Integer id) {
        return entityManager.find(StudentEntity.class,id );
    }

    @Override
    public void addStudent(StudentEntity studentEntity) {
            entityManager.persist(studentEntity);
    }

    @Transactional
    @Override
    public void updateStudent(StudentEntity studentEntity) {
        entityManager.merge(studentEntity);
    }

    @Override
    public List<StudentEntity> findByDayandMonth(Integer d, Integer m) {
        StringBuilder sql=new StringBuilder("SELECT s.* FROM student s WHERE DAY(birthday) ="+d+" AND MONTH(s.birthday)="+m+" ;");
        Query query=entityManager.createNativeQuery(sql.toString(), StudentEntity.class);
        return query.getResultList();
    }

}
