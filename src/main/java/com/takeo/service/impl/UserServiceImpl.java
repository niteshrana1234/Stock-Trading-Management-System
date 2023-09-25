package com.takeo.service.impl;


import com.takeo.model.Portfolio;
import com.takeo.model.User;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.repo.UserRepo;
import com.takeo.service.UserService;
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

        User usr = userRepo.save(user);

        if (usr != null) {
            return "Registration Successful";
        }
        return "Failed To Register";
    }

    @Override
    public String updateUser(UpdateUserDTO userDTO) {
        Optional<User> usr = userRepo.findByEmail(userDTO.getEmail());
        if(usr.isPresent()){
            User user = usr.get();
            BeanUtils.copyProperties(userDTO,user);
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
