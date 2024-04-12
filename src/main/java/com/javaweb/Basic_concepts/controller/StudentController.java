package com.javaweb.Basic_concepts.controller;

import com.javaweb.Basic_concepts.model.dto.StudentDTO;
import com.javaweb.Basic_concepts.model.request.AddOrUpdateStudentRequest;
import com.javaweb.Basic_concepts.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@Log4j2
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
//    @DeleteMapping ("/student-{ids}")
    @DeleteMapping ("/student/{id}")//thường thường ng ta viết ntn
    public void deletetStudent(@PathVariable Integer id) {// hiện tại vd này ta xóa 1 cái 1 thôi chưa cần dùng đến list
        studentService.deleteById(id);
    }

    //update + add
    @PostMapping("/student")
    public StudentDTO addOrUpdateStudent(@RequestBody AddOrUpdateStudentRequest addOrUpdateStudentRequest){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO = studentService.addOrUpdateSt(addOrUpdateStudentRequest);
        if (studentDTO != null){
            log.info("LƯU THÀNH CÔNG");
            return studentDTO;
        }
        log.info("LƯU KHÔNG THÀNH CÔNG");
        return studentDTO;
    }

    @GetMapping("/birthday")
    public List<StudentDTO> getHappyBirthday(@RequestParam(name="birthday") String birthday){
        log.info("birthday" + birthday);
        return studentService.getStudentByBirthDay(birthday);
    }
}



