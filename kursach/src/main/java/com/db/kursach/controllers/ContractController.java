package com.db.kursach.controllers;



import com.db.kursach.models.Contract;
import com.db.kursach.models.Employee;
import com.db.kursach.models.EstimateDTO;
import com.db.kursach.models.User;
import com.db.kursach.services.ContractService;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_FOREMAN', 'ROLE_DIRECTOR')")
@RequestMapping("/contracts")
public class ContractController {
    private final EmployeeService employeeService;
    private final ContractService contractService;
    private final MaterialService materialService;
    private final AppController appController;
    Contract contract;
    @GetMapping
    public String contracts(Model model){
        User user = appController.user;
        List<Contract> listContracts;
        if (user.isForeman()) listContracts = contractService.listContracts(user.getEmail());
        else listContracts = contractService.listContracts();
        listContracts.sort(Comparator.comparing(Contract::getConclusion).reversed());
        model.addAttribute("user", user);
        model.addAttribute("contracts",listContracts);
        return "contracts";
    }

    @GetMapping("/{id}")
    public String contractInfo(@PathVariable Long id,@RequestParam(name = "materialName",required = false) String materialName, Model model){
        String searchString = "";
        if (materialName != null) searchString =materialName;
        Contract contract = contractService.getContractById(id);
        model.addAttribute("user", appController.user);
        model.addAttribute("contract",contract);
        model.addAttribute("searchString", searchString);
        model.addAttribute("materials", materialService.listMaterials(materialName));
        return "contract-info";
    }
    @PostMapping("/create/start")
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR')")
    public String startContractCreation(Model model){
        model.addAttribute("user", appController.user);
        model.addAttribute("foremen", employeeService.listForemen());
        return "contract-creation";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR')")
    public String contractCreation(Contract contract){
        contractService.saveContract(contract);
        return "redirect:/contracts";
    }
    @PostMapping("/addMaterial")
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR', 'ROLE_FOREMAN')")
    public String addMaterialInContract(EstimateDTO estimateDTO){
        System.err.println(estimateDTO);
        contractService.addMaterialInContract(contractService.getContractById(estimateDTO.getContractId()), materialService.getMaterialById(estimateDTO.getMaterialId()), estimateDTO.getAmount());
        return "redirect:/contracts/" + estimateDTO.getContractId();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR')")
    public String deleteContract(@PathVariable Long id){
        contractService.deleteContract(id);
        return "redirect:/contracts";
    }

    @PostMapping("/{id}/done")
    @PreAuthorize("hasAnyAuthority('ROLE_FOREMAN')")
    public String changeStatus(@PathVariable Long id) {
        contractService.changeContractStatus(id);
        return "redirect:/contracts/" + id;
    }


}
