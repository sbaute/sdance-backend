package com.sdance_backend.sdance.entity;

import com.sdance_backend.sdance.enums.status.StudentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "phone_number",nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StudentStatus status;

    @ManyToMany(mappedBy = "students")
    private List<DanceClass> danceClasses;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
