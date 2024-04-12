package com.javaweb.Basic_concepts.converter;

import com.javaweb.Basic_concepts.Entity.Exam_ManagementEntity;
import com.javaweb.Basic_concepts.Entity.MajorEntity;
import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;
import com.javaweb.Basic_concepts.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentConverter {
    @Autowired
    private ModelMapper moderMapper;

    @Autowired
    private MajorRepository majorRepository;

    public StudentDTO toStudentDTO(StudentEntity studentEntity){
        MajorEntity majorEntity=studentEntity.getMajor();
        List<Exam_ManagementEntity> examManagementEntities=studentEntity.getExamManagements();

        String sujectName="";
        for(Exam_ManagementEntity em : examManagementEntities){
            sujectName+=em.getSubject().getSubject_Name()+",";
        }
        StudentDTO studentDTO=moderMapper.map(studentEntity,StudentDTO.class);
        studentDTO.setSubjectName(sujectName.substring(0,sujectName.length()-1));
        studentDTO.setMajorName(majorEntity.getMajor_Name());
        return studentDTO;
    }

    public StudentEntity toStudentEntity(AddOrUpdateStudentRequest addOrUpdateStudentRequest){
        StudentEntity studentEntity=moderMapper.map(addOrUpdateStudentRequest,StudentEntity.class);
        MajorEntity major=majorRepository.findByName(addOrUpdateStudentRequest.getMajorName());
        if(major==null){
            major=new MajorEntity();
            major.setMajor_Name(addOrUpdateStudentRequest.getMajorName());
            majorRepository.addMajor(major);
        }
        studentEntity.setMajor(major);
        return  studentEntity;
    }
}
