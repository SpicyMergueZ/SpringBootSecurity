package com.alibou.security.user;


import java.util.Arrays;
import java.util.Collection;

//import javax.management.relation.Role;

//import org.hibernate.mapping.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
        
@Id
@GeneratedValue    
private Integer id;

private String firstname;

private String lastname;

private String email;

private String password;

@Enumerated(EnumType.STRING)
private Roles role;

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return Arrays.asList(new SimpleGrantedAuthority(role.name()));
}

@Override
public String getUsername() {
    // TODO Auto-generated method stub
    return null;
}

@Override
public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
}

@Override
public String getPassword() {
    // TODO Auto-generated method stub
    return password;
}








}
