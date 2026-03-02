package com.sdance_backend.sdance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdance_backend.sdance.enums.danceClass.ClassLevel;
import com.sdance_backend.sdance.enums.danceClass.Days;
import com.sdance_backend.sdance.enums.danceClass.Hour;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "dance_class")
public class DanceClass {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    private String className;

    @Enumerated(EnumType.STRING)
    private Days daysOfWeek;

    @Enumerated(EnumType.STRING)
    private Hour classTime;

    @Enumerated(EnumType.STRING)
    private ClassLevel level;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    @Column(length = 500)
    private String description;

    @Column(name = "room")
    private String room;

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
