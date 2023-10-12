package com.db.kursach.controllers;

import com.db.kursach.models.Material;
import com.db.kursach.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_FOREMAN', 'ROLE_DIRECTOR')")
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialService materialService;
    private final AppController appController;

    @GetMapping
    public String materials(@RequestParam(required = false) String name,@RequestParam(required = false) Boolean sortedByPrice , Model model){
        if(sortedByPrice != null && sortedByPrice) model.addAttribute("materials", materialService.getMaterialSortedByPrice(name));
        else model.addAttribute("materials", materialService.listMaterials(name));
        String searchString = "";
        if (name != null) searchString = name;
        boolean sorted = false;
        if (sortedByPrice != null && sortedByPrice) sorted = true;
        model.addAttribute("user", appController.user);
        model.addAttribute("searchString", searchString);
        model.addAttribute("sortedByPrice", sorted);
        return "materials";
    }

    @PostMapping("/create")
    public String createMaterial(Material material){
        materialService.saveMaterial(material);
        return "redirect:/materials";
    }
    @PostMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
        return "redirect:/materials";
    }
    @GetMapping("/edit/{id}")
    public String editMaterial(@PathVariable Long id,Model model)
    {
        model.addAttribute("user", appController.user);
        model.addAttribute("material", materialService.getMaterialById(id));
        return "material-edit";
    }
    @PostMapping("/editing")
    public String editingMaterial(Material material)
    {
        materialService.editMaterial(material);
        return "redirect:/materials";
    }

}
