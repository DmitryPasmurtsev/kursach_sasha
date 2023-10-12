package com.db.kursach.models;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
public class EstimateKey implements Serializable {
    private Contract contract;
    private Material material;
    public EstimateKey(Contract contract, Material material) {
        this.contract = contract;
        this.material = material;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
}
