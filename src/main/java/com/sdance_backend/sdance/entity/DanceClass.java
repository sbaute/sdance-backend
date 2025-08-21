package com.sdance_backend.sdance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dance_class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanceClass {

    @Id
    private UUID id = UUID.randomUUID();

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
