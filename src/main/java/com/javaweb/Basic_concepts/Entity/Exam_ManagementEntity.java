package com.javaweb.Basic_concepts.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="exam_management")
public class Exam_ManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectEntity subject;
}
