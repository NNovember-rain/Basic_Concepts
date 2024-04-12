package com.javaweb.Basic_concepts.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="student")
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "gender")
    private String gender;

    @Column(name = "avg")
    private Float avg;

    @ManyToOne
    @JoinColumn(name="major_id")
    private MajorEntity major;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private List<Exam_ManagementEntity> examManagements=new ArrayList<>();

}
