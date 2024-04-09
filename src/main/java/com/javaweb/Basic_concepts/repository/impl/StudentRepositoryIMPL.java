package com.javaweb.Basic_concepts.repository.impl;


import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.repository.StudentRepository;
import com.javaweb.Basic_concepts.utils.BirthdayUtil;
import com.javaweb.Basic_concepts.utils.StringUtil;
import com.javaweb.Basic_concepts.utils.isNumberUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryIMPL implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void checkJoinTable(Map<String, Object> params, List<String> subjectClass, StringBuilder sql){
        if(params.containsKey("avgPonit") || subjectClass!=null){
            sql.append("INNER JOIN exam_management em ON s.id=em.student_id");
            sql.append(" INNER JOIN subject sj ON sj.id=em.subject_id ");
        }
    }
    public void checkQuerryNormal(Map<String, Object> params, List<String> subjectClass,StringBuilder where){
        for(String it:params.keySet()){
            if( !it.equals("subjectClass") &&  !it.startsWith("avg") &&  !it.startsWith("birthday")){
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
        }

        if(subjectClass!=null){
            List<String> subjects=new ArrayList<>();
            for(String s:subjectClass){
                subjects.add("'"+s+"'");
            }
            for(String s:subjectClass){

            }
            where.append(" AND sj.name IN (" +String.join(",",subjects )+") ");
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

        where.append(" GROUP BY s.id; ");
        sql.append(where.toString());

        return solveResult(sql);
    }
}
