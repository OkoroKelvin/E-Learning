package com.ileiwe.data.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate date;


    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToOne
    private LearningParty LearningParty;



    @ManyToMany
    private List<Course> enrolledCourses;

}
