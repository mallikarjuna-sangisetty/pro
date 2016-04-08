package com.project.traffic.controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.exception.StandardException;
import com.project.traffic.model.User;
import com.project.traffic.service.UserService;
import com.project.traffic.serviceImpl.UserServiceImpl;
import com.project.traffic.uiImpl.AdminMenu.UserManageEnum;
import com.project.traffic.util.InputFromConsoleUtil;

public class UserController {

    private static UserService service;

    static{
        service = new UserServiceImpl();
    }

    public void saveUser(User user){
        service.save(user);
    }

    public boolean validateLogin(String username,String password){
        return service.validateLogin(username,password);
    }

    public boolean findByUsername(String username){
        return service.findByUsername(username);
    }

    public User findByLogin(String username){
        return service.findByLogin(username);
    }

    public void updateUser(User user){
        service.update(user);
    }

    public List<User> findAllUsers() {
        return service.findAll();

    }
    public void userManageMenu(User user) throws IOException{

        UserManageEnum choice = null;
        try {
            do{
                choice = InputFromConsoleUtil.enumValues(UserManageEnum.class);

                switch (choice) {
                case ACTIVATE:

                    int reply = JOptionPane.showConfirmDialog(null, "Do you want to Activate User?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(reply == JOptionPane.YES_OPTION){
                        user.setStatus(StatusType.ACTIVE.value());
                        updateUser(user);
                        JOptionPane.showMessageDialog(null,"User successfully activated","Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case DEACTIVATE:
                    int reply1 = JOptionPane.showConfirmDialog(null, "Do you want to De-Activate User?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(reply1 == JOptionPane.YES_OPTION){
                        user.setStatus(StatusType.INACTIVE.value());
                        updateUser(user);
                        JOptionPane.showMessageDialog(null,"User successfully de-activated","Information",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case BACK:
                    break;
                }
            }while(choice != UserManageEnum.BACK);
        } catch (StandardException e) {
            System.out.println(e.getMessage());
        }
    }
}
