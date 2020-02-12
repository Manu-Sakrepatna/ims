package com.example.ims.controllers;

//import com.example.ims.auth.AppUserDetailService;
//import com.example.ims.auth.AuthUser;
//import com.example.ims.auth.JwtUtils;
//import com.example.ims.auth.TokenResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/api/v1/auth")
//@CrossOrigin
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private AppUserDetailService appUserDetailService;
//
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> login(@RequestBody AuthUser user) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        //  If we reached this far, we can ask the JwtUtils to provide us the token
//        final UserDetails userDetails = appUserDetailService
//                .loadUserByUsername(user.getUsername());
//
//        final String jwt = jwtUtils.generateToken(userDetails);
//
//        return ResponseEntity.ok(new TokenResponse(jwt));
//    }
//}
