/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.model.Role;
import com.met.vernamCip.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mladen
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userService.findUserByEmail(email);
        List<GrantedAuthority> auth = getUserAuthority(u.getRoles());
        return buildUserForAuthentication(u, auth);
    }
    
    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
    
    private UserDetails buildUserForAuthentication(User u, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(),
                u.isAc(), true, true, true, authorities);
    }
    
}
