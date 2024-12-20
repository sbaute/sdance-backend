package com.sdance_backend.sdance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class DanceClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String className;

    @Enumerated(EnumType.STRING)
    private Days daysOfWeek;

    @Enumerated(EnumType.STRING)
    private Hour classTime;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    @ManyToMany
    @JoinTable(
            name = "student_danceclass",
            joinColumns = @JoinColumn(name = "dance_class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonBackReference
    private List<Student> students;

}
