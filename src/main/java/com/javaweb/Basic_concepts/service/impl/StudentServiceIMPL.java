package com.javaweb.Basic_concepts.service.impl;

import com.javaweb.Basic_concepts.Entity.StudentEntity;
import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.repository.StudentRepository;
import com.javaweb.Basic_concepts.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceIMPL implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper moderMapper;
    @Override
    public List<StudentDTO> findAll(Map<String, Object> params, List<String> subjectClass) {


        List<StudentEntity> studentEntities= studentRepository.findAll(params,subjectClass);
        List<StudentDTO> studentDTOS=new ArrayList<>();
        for(StudentEntity it:studentEntities){
            studentDTOS.add(moderMapper.map(it,StudentDTO.class));
        }
        return studentDTOS;
    }
}
