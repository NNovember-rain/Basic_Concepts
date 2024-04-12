package com.javaweb.Basic_concepts.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="subject")
public class SubjectEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_Name")
    private String subject_Name;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    private List<Exam_ManagementEntity> examManagements=new ArrayList<>();
}
