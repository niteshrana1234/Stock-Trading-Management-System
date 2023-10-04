package com.takeo.service;

import com.takeo.model.Portfolio;
import com.takeo.model.User;
import com.takeo.payloads.RegisterUserDTO;
import com.takeo.payloads.UpdateUserDTO;

import java.util.List;

public interface UserService {
    public String registerAccount(RegisterUserDTO user);
    public String updateUser(UpdateUserDTO userDTO);
    public User getUser(int id);
    public String deleteAccount(int id);
    public List<Portfolio> listOfPortfolio(int userId);
    public String changePassword(String email,String password,String newPassword);
    public String userLogIn(String email, String password);

}
