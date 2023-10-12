package com.db.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "estimates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "id.contract",
                joinColumns = @JoinColumn(name = "contract_id")),
        @AssociationOverride(name = "id.material",
                joinColumns = @JoinColumn(name = "material_id")) })
public class Estimate {
    EstimateKey id = new EstimateKey();
    private int amount;
    @Column(name="material_id")
    private Material material;
    @Column(name="contract_id")
    private Contract contract;
    @EmbeddedId
    public EstimateKey getId() {
        return id;
    }
    public void setId(EstimateKey id) {
        this.id = id;
    }
    @Column(name="material_amount")
    public int getAmount() {
        return amount;
    }
    @Column(name="material_amount")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Transient
    public Material getMaterial() {
        return material;
    }

    public void setProduct(Material material) {
        this.material = material;
    }
    @Transient
    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
