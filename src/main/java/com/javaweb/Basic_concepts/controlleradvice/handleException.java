package com.javaweb.Basic_concepts.controlleradvice;

import CustomException.FieldRequiredException;
import CustomException.IdExistException;
import com.javaweb.Basic_concepts.model.response.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class handleException extends ResponseEntityExceptionHandler {

        @ExceptionHandler(FieldRequiredException.class)
        public ResponseEntity<Object> FieldException(FieldRequiredException ex){
            StudentResponse errorResponseDTO = new StudentResponse();
            errorResponseDTO.setMassage(ex.getMessage());
            List<String> details = new ArrayList<>();
            details.add("please,enter the full field ");
            errorResponseDTO.setDetails(details);
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
        }

    @ExceptionHandler(IdExistException.class)
    public ResponseEntity<Object> IdException(IdExistException ex){
        StudentResponse errorResponseDTO = new StudentResponse();
        errorResponseDTO.setMassage(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("You must choose the studentId Existed ");
        errorResponseDTO.setDetails(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
    }
}
