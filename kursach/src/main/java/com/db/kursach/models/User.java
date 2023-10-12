package com.db.kursach.models;


import com.db.kursach.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name="role")
    private Role role;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "employee_id")
    private Employee employee;

    public boolean isForeman() {return role.equals(Role.ROLE_FOREMAN);}
    public boolean isDirector() {return role.equals(Role.ROLE_DIRECTOR);}
    public Employee getEmployee(){
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
