package com.db.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    //надо поменять тип данных
    //дата заключения договора
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "conclusion_date")
    private LocalDate conclusion;
    //дата выполнения
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "completion_date")
    private LocalDate completion;

    // стоимость заказа надо генерить из стоимости материалов + 15 процентов
    @Column(name = "contract_price")
    private Double price;

    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "contract_info", columnDefinition = "text")
    private String description;
    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "id.contract",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estimate> estimates;

}
