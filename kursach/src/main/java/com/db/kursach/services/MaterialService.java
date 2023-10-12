package com.db.kursach.services;

import com.db.kursach.models.Estimate;
import com.db.kursach.models.Material;
import com.db.kursach.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> listMaterials(String name){
        if(name!=null) return materialRepository.findByNameContaining(name);
        return materialRepository.findAll();
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findById(id).orElse(null);
    }
    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }
    public void deleteMaterial(Long id){
        Material material = getMaterialById(id);
        for(Estimate estimate : material.getEstimates()) {
            estimate.getId().getContract().setPrice(estimate.getId().getContract().getPrice() - material.getPrice() * estimate.getAmount());
        }
        materialRepository.deleteById(id);
    }

    public void editMaterial(Material material){
        material.setEstimates(getMaterialById(material.getId()).getEstimates());
        materialRepository.save(material);
    }

    public List<Material> getMaterialSortedByPrice(String name) {
        return materialRepository.findByNameContainingOrderByPriceDesc(name);
    }
}
