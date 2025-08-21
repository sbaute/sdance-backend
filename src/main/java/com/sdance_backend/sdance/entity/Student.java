package com.sdance_backend.sdance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "phone_number",nullable = false, unique = true)
    private String phoneNumber;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<DanceClass> danceClasses;

}
