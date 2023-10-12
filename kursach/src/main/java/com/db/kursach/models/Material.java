package com.db.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "materials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long id;
    @Column(name = "material_name")
    private String name;
    @Column(name = "material_description")
    private String description;
    @Column(name = "material_price")
    private Float price;

    @OneToMany(mappedBy = "id.material",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estimate> estimates;

    public String toStringPrice(){return price.toString();}
}
