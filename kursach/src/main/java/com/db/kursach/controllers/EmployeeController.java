package com.db.kursach.controllers;

import com.db.kursach.enums.Role;
import com.db.kursach.models.Employee;
import com.db.kursach.models.User;
import com.db.kursach.repositories.EmployeeRepository;
import com.db.kursach.repositories.UserRepository;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_FOREMAN', 'ROLE_DIRECTOR')")
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AppController appController;
    private final UserService userService;
    @GetMapping
    public String employees(@RequestParam(name = "fullName",required = false) String fullName, Model model){
        model.addAttribute("employees",employeeService.listEmployees(fullName));
        String searchString = "";
        if (fullName != null) searchString =fullName;
        model.addAttribute("user",appController.user);
        model.addAttribute("searchString", searchString);
        return "employees";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR')")
    @PostMapping("/create")
    public String createEmployee(Employee employee) throws IOException{
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }
    @PostMapping("/{id}/addImage")
    public String createImage(@RequestParam("file") MultipartFile file,@PathVariable Long id)throws IOException{
        employeeService.saveImage(file,id);
        return "redirect:/employees/edit/{id}";
    }
    @PostMapping("/{id}/deleteImage")
    public String deleteImage(@PathVariable Long id){
        employeeService.deleteImage(id);
        return "redirect:/employees/edit/{id}";
    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_DIRECTOR')")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String employeeInfo(@PathVariable Long id,Model model){
        Employee employee=employeeService.getEmployeeById(id);
        model.addAttribute("user", appController.user);
        model.addAttribute("employee",employee);
        return "employee-info";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getEmployeeImage(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);
        return ResponseEntity.ok()
                .body(new InputStreamResource(new ByteArrayInputStream(employee.getImage_bytes())));
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id,Principal principal, Model model)
    {   if(userService.getUserByPrincipal(principal).getEmployee().getId()!=id &&
            userService.getUserByPrincipal(principal).isForeman()) return "redirect:/employees/" + id;
        Employee employee=employeeService.getEmployeeById(id);
        model.addAttribute("user",appController.user);
        model.addAttribute("employee",employee);
        return "employee-edit";
    }
    @PostMapping("/editing")
    public String editingEmployee(Employee employee)
    {
        if(appController.user.getEmployee().getId()!=employee.getId() &&
                appController.user.isForeman()) return  "redirect:/employees/" + employee.getId();
        employeeService.editEmployee(employee);
        return "redirect:/employees/" + employee.getId();
    }

}