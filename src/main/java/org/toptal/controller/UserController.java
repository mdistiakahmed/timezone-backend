package org.toptal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.toptal.config.TokenProvider;
import org.toptal.model.*;
import org.toptal.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/getAllUser", method = RequestMethod.GET)
    public List<UserDto> getAllUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserDto user){
        UserDto userDto = userService.addUser(user);
         if(userDto == null) {
             return  ResponseEntity.status(HttpStatus.CONFLICT).body("Existing account this email address");

         }else {
             return new ResponseEntity<>(HttpStatus.CREATED);
         }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/deleteUsers", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsers(@RequestBody UserDelete userDelete){
        userService.bulkDelete(userDelete.getEmailIds());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public User updateUser(@RequestBody UserUpdate userUpdate){
        return userService.updateUser(userUpdate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/updateUserPut", method = RequestMethod.PUT)
    public User updateUserPut(@RequestBody UserUpdate userUpdate){
        return userService.updateUser(userUpdate);
    }

}