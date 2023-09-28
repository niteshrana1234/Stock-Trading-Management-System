package com.takeo.controller;

import com.takeo.model.User;
import com.takeo.payloads.RegisterUserDTO;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.payloads.UserLoginDTO;
import com.takeo.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody RegisterUserDTO user) {

        String register = userService.registerAccount(user);
        Map<String,String> response = new HashMap<>();
        response.put("message",register);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verifyOtp/{otp}")
    public ResponseEntity<Map<String,String>> verifyOtp(@PathVariable String otp){

       String verifyOtp = userService.verifyOtp(otp);
       Map<String,String> response = new HashMap<>();
       response.put("message",verifyOtp);

       return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") int id) {

        String response = userService.deleteAccount(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", response);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> userLogin(@Valid @RequestBody UserLoginDTO loginDTO) {

        String userLogIn = userService.userLogIn(loginDTO.getEmail(), loginDTO.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("message", userLogIn);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody UpdateUserDTO userDTO){

      String userUpdate =  userService.updateUser(userDTO);
      Map<String,String> response = new HashMap<>();
      response.put("message",userUpdate);

      return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
