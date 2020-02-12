package com.example.ims.auth;

//import com.example.ims.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AppUserDetailService implements UserDetailsService {
//
//    @Autowired
//    UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        com.example.ims.models.User user = userService.fetchUserByUserID(s);
//        if(user == null){
//            throw new UsernameNotFoundException("User not found with userid: " + s);
//        }
//
//        return new User(s, user.getPassword(),new ArrayList<>());
////        return new User(s, "password", new ArrayList<>());
//
//    }
//
//}
