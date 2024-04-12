package com.javaweb.Basic_concepts.service.impl;

import com.javaweb.Basic_concepts.Entity.Exam_ManagementEntity;
import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.Entity.SubjectEntity;
import com.javaweb.Basic_concepts.checkrequire.CheckRequire;
import com.javaweb.Basic_concepts.converter.StudentConverter;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;
import com.javaweb.Basic_concepts.repository.Exam_ManagementRepository;
import com.javaweb.Basic_concepts.repository.StudentRepository;
import com.javaweb.Basic_concepts.repository.SubjectRepository;
import com.javaweb.Basic_concepts.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class StudentServiceIMPL implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private  StudentConverter studentConverter;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    Exam_ManagementRepository examManagementRepository;

    @Autowired
    CheckRequire checkRequire;

    @Override
    public List<StudentDTO> findAll(Map<String, Object> params, List<String> subjectClass) {
        try {
            List<StudentEntity> studentEntities= studentRepository.findAll(params,subjectClass);
            List<StudentDTO> studentDTOS=new ArrayList<>();
            if (studentEntities != null) {
                for(StudentEntity it:studentEntities){
                    studentDTOS.add(studentConverter.toStudentDTO(it));
                }
            }
            return studentDTOS;
        } catch (Exception e) {
            log.info("Exception: ", e);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try{
            StudentEntity studentEntity = studentRepository.findById(id);
            studentRepository.deleteStudent(studentEntity);
        } catch (Exception e) {
            log.info("Exception: ", e);
        }
    }

    @Override
    public StudentDTO addOrUpdateSt(AddOrUpdateStudentRequest addOrUpdateStudentRequest) {
        checkRequire.checkField(addOrUpdateStudentRequest); // anh xem lại đoạn này e xử lí gửi thiếu field khi add or update
        try{
//             check chỗ này thấy không có tác dụng lắm, vì không có kểu trả về

            StudentEntity studentEntity=studentConverter.toStudentEntity(addOrUpdateStudentRequest);
            if(studentEntity.getId()==null){
                studentRepository.addStudent(studentEntity);
            }
            else {
                studentRepository.updateStudent(studentEntity);
            }
            String[] s= addOrUpdateStudentRequest.getSubjectName().split(",");
            for(String it:s){
                SubjectEntity subjectEntity=subjectRepository.findByName(it);
                if(subjectEntity==null){
                    subjectEntity=new SubjectEntity();
                    subjectEntity.setSubject_Name(it);
                    subjectRepository.addSubject(subjectEntity);
                }
                Exam_ManagementEntity examManagementEntity=new Exam_ManagementEntity();
                examManagementEntity.setStudent(studentEntity);
                examManagementEntity.setSubject(subjectEntity);
                subjectEntity.getExamManagements().add(examManagementEntity);
                studentEntity.getExamManagements().add(examManagementEntity);
                examManagementRepository.addExamManage(examManagementEntity);
            }
            return studentConverter.toStudentDTO(studentEntity);
        }catch (Exception e) {
            log.info("Exception: ", e);
        }
        return null;
    }

    @Override
    public List<StudentDTO> getStudentByBirthDay(String birthday) {
        String[] tmp=birthday.split("-");
        Integer day=Integer.parseInt(tmp[0]);
        Integer month=Integer.parseInt(tmp[1]);
        List<StudentEntity> studentEntities=studentRepository.findByDayandMonth(day,month);

        List<StudentDTO> studentDTOS=new ArrayList<>();
        for(StudentEntity it:studentEntities){
            studentDTOS.add(studentConverter.toStudentDTO(it));
        }
        return studentDTOS;
    }
}
