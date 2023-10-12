package com.db.kursach.models;

import lombok.Data;

@Data
public class EstimateDTO {
    private Long materialId;
    private Long contractId;
    private int amount;

    @Override
    public String toString() {
        return "EstimateDTO{" +
                "materialId=" + materialId +
                ", contractId=" + contractId +
                ", amount=" + amount +
                '}';
    }
}
