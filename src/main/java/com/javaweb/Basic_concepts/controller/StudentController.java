package com.javaweb.Basic_concepts.controller;

import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;
import com.javaweb.Basic_concepts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    //tim kiem
    @GetMapping("/student")
    public List<StudentDTO> getStudent(@RequestParam Map<String,Object> params,
                                       @RequestParam(name="subjectClass",required = false) List<String> subjectClass) {
        List<StudentDTO> studentDTOS=studentService.findAll(params,subjectClass);
        return studentDTOS;
    }
    //xoa
    @DeleteMapping ("/student-{ids}")
    public void deletetStudent(@PathVariable List<Integer> ids) {
        studentService.deleteById(ids);
    }

    //update + add
    @PostMapping("/api/student")
    public StudentDTO addOrUpdateStudent(@RequestBody AddOrUpdateStudentRequest addOrUpdateStudentRequest){
        return  studentService.addOrUpdateSt(addOrUpdateStudentRequest);
    }

    @GetMapping("/api/birthday")
    public List<StudentDTO> getHappyBirthday(@RequestParam(name="birthday") String birthday){
        return studentService.getStudentByBirthDay(birthday);
    }
}



