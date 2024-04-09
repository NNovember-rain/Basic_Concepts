package com.javaweb.Basic_concepts.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="student")
public class StudentEntity {
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
    private Float AVG;

    @ManyToOne
    @JoinColumn(name="major_id")
    private MajorEntity major;

    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
    private List<SubjectEntity> subjects=new ArrayList<>();


}
