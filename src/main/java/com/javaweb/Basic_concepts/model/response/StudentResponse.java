package com.javaweb.Basic_concepts.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class StudentResponse {
    private String Massage;
    private List<String> details = new ArrayList<String>();
}
