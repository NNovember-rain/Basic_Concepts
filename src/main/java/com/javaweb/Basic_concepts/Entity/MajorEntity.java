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
@Table(name="major")
public class MajorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "major_Name")
    private String major_Name;

    @OneToMany(mappedBy = "major",fetch = FetchType.LAZY)
    private List<StudentEntity> students=new ArrayList<>();


}
