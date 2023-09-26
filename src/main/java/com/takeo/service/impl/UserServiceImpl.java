package com.takeo.service.impl;


import com.takeo.execption.UserNotFoundException;
import com.takeo.model.Portfolio;
import com.takeo.model.User;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.repo.UserRepo;
import com.takeo.service.UserService;
import com.takeo.utils.EmailSender;
import com.takeo.utils.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String registerAccount(User user) {
        String message = "User already exist";

        Optional<User> usr = userRepo.findByEmail(user.getEmail());
        if (usr.isEmpty()) {
            String otp = EmailSender.sendOtp(user.getEmail());
            message = "Faild to send otp";
            if (otp != null) {
                user.setOtp(otp);
                User savedUser = userRepo.save(user);
                if (savedUser != null) {
                    return "Otp send to Respected email successfully";
                }
            }
        } else {
            throw new UserNotFoundException("User already exist with " + user.getEmail());
        }
        return message;

    }

    public String verifyOtp(String otp) {
        String message = "Registration failed";
        Optional<User> users = userRepo.findByOtp(otp);
        if (users.isPresent()) {
            message = "Invalid otp";
            if (otp.equals(users.get().getOtp())) {
                User user = users.get();
                String randomPass = PasswordGenerator.getRandomPassword();
                user.setPassword(randomPass);
                user.setOtp("");
                User savedUser = userRepo.save(user);
                String sendPass = EmailSender.sendRandomPassword(user.getEmail(), randomPass);
                if (savedUser!= null && sendPass!=null) {
                    return "Registered successfully";
                }
            }

        }
        return message;
    }

    @Override
    public String updateUser(UpdateUserDTO userDTO) {
        Optional<User> usr = userRepo.findByEmail(userDTO.getEmail());
        if (usr.isPresent()) {
            User user = usr.get();
            BeanUtils.copyProperties(userDTO, user);
            user.setUpdatedDate(new Date());
            userRepo.save(user);
            return "Updated Successfully";
        }

        return "Update failed";
    }

    @Override
    public User getUser(int id) {
        Optional<User> usr = userRepo.findById(id);
        if (usr.isPresent()) {
            return usr.get();
        }
        return null;
    }

    @Override
    public String deleteAccount(int id) {
        User user = getUser(id);
        String msg = "";
        if (user != null) {
            userRepo.delete(user);
            msg = "Deleted successfully";
        } else msg = "Deletion failed";
        return msg;
    }

    @Override
    public List<Portfolio> listOfPortfolio(int userId) {
        return null;
    }

    @Override
    public String changePassword(int id) {
        return null;
    }

    @Override
    public String userLogIn(String email, String password) {

        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            User userDetail = user.get();
            if (userDetail.getPassword().equals(password)) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        }
        return "Invalid email address";
    }

}
