package com.db.kursach.services;

import com.db.kursach.enums.Role;
import com.db.kursach.models.Contract;
import com.db.kursach.models.Employee;
import com.db.kursach.models.Position;
import com.db.kursach.models.User;
import com.db.kursach.repositories.EmployeeRepository;
import com.db.kursach.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    public final EmployeeRepository employeeRepository;

    public List<Employee> listEmployees(String fullName){
        if(fullName!=null) return employeeRepository.findByNameContainingIgnoreCase(fullName);
        return employeeRepository.findAll();
    }
    public void saveEmployee(Employee employee) {
        log.info("Saving new Employee.{}",employee);
        employee.setDate(LocalDate.now());
        employee.setPosition(new Position());
        employee.getPosition().setId(2L);
        employeeRepository.save(employee);
    }
    public void saveImage(MultipartFile file,Long id) throws IOException {
        Employee employee=getEmployeeById(id);
        if(!file.isEmpty()) {
            employee.setImage_bytes(file.getBytes());
            employeeRepository.save(employee);
        }
    }
    public void deleteEmployee(Long id){
        Employee employee = getEmployeeById(id);
        for (Contract contract : employee.getContracts()) {
            contract.setEmployee(null);
        }
        employeeRepository.deleteById(id);
    }
    public void deleteImage(Long id){
        Employee employee= getEmployeeById(id);
        employee.setImage_bytes(null);
        employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public void editEmployee(Employee employee){
        Employee deprecatedEmployee = employeeRepository.findById(employee.getId()).orElseThrow();
        employee.setContracts(deprecatedEmployee.getContracts());
        employee.setUser(deprecatedEmployee.getUser());
        employee.setPosition(deprecatedEmployee.getPosition());
        employee.setImage_bytes(deprecatedEmployee.getImage_bytes());
        employeeRepository.save(employee);
    }
    public List<Employee> listForemen() {
        return employeeRepository.findByPosition_Name(Role.ROLE_FOREMAN.name);
    }

    public List<Employee> listEmployeesSortedByExperience(String fullName) {
        return employeeRepository.findByNameContainingOrderByExperienceDesc(fullName);
    }

    public Employee getByEmail(String email) {
       return employeeRepository.findByEmail(email);
    }

}