package com.javaweb.Basic_concepts.controller;

import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public List<StudentDTO> getStudent(@RequestParam Map<String,Object> params,
                                       @RequestParam(name="subjectClass",required = false) List<String> subjectClass) {

        List<StudentDTO> studentDTOS=studentService.findAll(params,subjectClass);
        return studentDTOS;
    }
}



