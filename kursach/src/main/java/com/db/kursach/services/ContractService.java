package com.db.kursach.services;



import com.db.kursach.models.*;
import com.db.kursach.repositories.ContractRepository;
import com.db.kursach.repositories.EstimateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final EstimateRepository estimateRepository;
    public List<Contract> listContracts(){
        return contractRepository.findAll();
    }
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    public void addMaterialInContract(Contract contract, Material material, int amount) {
        Estimate estimate;
        if(estimateRepository.findById(new EstimateKey(contract, material)).isPresent()) {
            estimate = estimateRepository.findById(new EstimateKey(contract, material)).get();
            estimate.setAmount(estimate.getAmount()+amount);
        } else {
            estimate = new Estimate();
            estimate.setContract(contract);
            estimate.setMaterial(material);
            estimate.setAmount(amount);
            estimate.setId(new EstimateKey(contract, material));
        }
        contract.setPrice(contract.getPrice() + material.getPrice() * amount);
        contract.getEstimates().add(estimate);
        saveContract(contract);
    }
    public void saveContract(Contract contract) {
        contract.setIsDone(false);
        contractRepository.save(contract);
    }
    public void deleteContract(Long id){
        contractRepository.deleteById(id);
    }

    public Contract deleteOrderPosition(int positionIndex, Contract contract) {
        //contract.getOrderComposition().remove(positionIndex);
        return contract;
    }

    public List<Contract> listContracts(String email) {
        return contractRepository.findByEmployeeEmail(email);
    }

    public void changeContractStatus(Long id) {
        Contract contract = getContractById(id);
        contract.setIsDone(true);
        contractRepository.save(contract);
    }

    public void setNewForeman(Long id, Employee employee) {
        Contract contract = getContractById(id);
        contract.setEmployee(employee);
        contractRepository.save(contract);
    }
}
