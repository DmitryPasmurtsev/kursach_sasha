package com.db.kursach.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "employee_full_name")
    private String name;
    @Column(name = "employee_phone_number")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="experience")
    private Integer experience;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "employment_date")
    private LocalDate date;
    @Lob
    private byte[] image_bytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", columnDefinition = "bigint default 2")
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Contract> contracts;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public String amountOfDoneContracts() {
        if (contracts == null) return "0";
        return ""+contracts.stream().filter(Contract::getIsDone).toList().size();
    }
}
