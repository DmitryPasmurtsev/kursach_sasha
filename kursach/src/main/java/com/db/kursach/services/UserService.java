package com.db.kursach.services;

import com.db.kursach.enums.Role;
import com.db.kursach.models.Employee;
import com.db.kursach.models.User;
import com.db.kursach.repositories.EmployeeRepository;
import com.db.kursach.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        if(userRepository.findByEmail(user.getEmail())!=null || employeeRepository.findByEmail(user.getEmail())==null||userRepository.findByLogin(user.getLogin())!=null){
            return false;
        }
        Employee employee = employeeRepository.findByEmail(user.getEmail());
        user.setEmployee(employee);
        user=setUserRole(employee, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new user with login {}",user.getLogin());
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal==null)return new User();
        return userRepository.findByLogin(principal.getName());
    }

    public User setUserRole(Employee employee, User user) {
        switch (Math.toIntExact(employee.getPosition().getId())){
            case 1: user.setRole(Role.ROLE_DIRECTOR); break;
            default: user.setRole(Role.ROLE_FOREMAN); break;
        }
        return user;
    }

    public void editUser(Long id, User user) {
        User userToEdit = userRepository.findById(id).orElseThrow();
        userToEdit.setLogin(user.getLogin());
        userToEdit.setPassword(user.getPassword());
        userRepository.save(userRepository.findById(id).orElseThrow());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String doPasswordEncode(String password) {
        return passwordEncoder.encode(password);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void updatePrincipal(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
