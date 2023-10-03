//package com.douzone.rest.auth.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Table(name="member")
//
//public class User implements UserDetails {
//    private Long id;
//    private String password;
//    private String email;
//    public User (String email, String password, String auth){
//        this.email=email;
//        this.password=password;
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        return List.of(new SimpleGrantedAuthority("user"));
//    }
//
//    @Override
//    public String getPassword(){
//        return password;
//    }
//
//    @Override
//    public
//
//
//
//}
