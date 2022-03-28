package com.comp.controller;

import com.comp.models.Book;
import com.comp.models.Role;
import com.comp.models.User;
import com.comp.services.RoleService;
import com.comp.services.UserService;
import com.comp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    public String users(Model model) {
        if( Session.getUser().getRole().getRoleName().equals("ADMIN") || Session.getUser().getRole().getRoleName().equals("WORKER")){
            List<User> users = new ArrayList<>();
            List<User> userList = userService.listAllUser();
            for (User user : userList)
                if (user.getRole().getRoleName().equals("CUSTOMER"))
                    users.add(user);
            model.addAttribute("users",users);
            model.addAttribute("role","Users");
            model.addAttribute("sessionUser",Session.getUser());
            return "users";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String editUser(Model model){
        if(Session.getUser()!=null){
            return "redirect:/";
        }
        else{
            User user = new User();
            user.setId(-1);
            model.addAttribute("user", user);
            model.addAttribute("role_id",3);
            model.addAttribute("user_id",-1);
            return "editUser";
        }
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam("id") int id, Model model){
        if( Session.getUser().getRole().getRoleName().equals("ADMIN") || Session.getUser().getRole().getRoleName().equals("WORKER")){
            if(id!=-1) {
                User user = userService.getUser(id);
                model.addAttribute("user", user);
            }
            else{
                User user = new User();
                user.setId(-1);
                model.addAttribute("user", user);
            }
            model.addAttribute("role_id",3);
            model.addAttribute("user_id",id);
            return "editUser";
        }
        else return "redirect:/";
    }

    @PostMapping("/editUser")
    public String login(@RequestParam("first_name") String firstName,
                        @RequestParam("last_name") String lastName,
                        @RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email,
                        @RequestParam("phone") String phone,
                        @RequestParam("birth") String birth,
                        @RequestParam("role_id") int roleId,
                        @RequestParam("user_id") int id) throws ParseException {
            if (id != -1) {
            User user = userService.getUser(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setRoleId(roleId);
            user.setBirth_date(new Date(new SimpleDateFormat("yyyy-mm-dd").parse(birth).getTime()));
            userService.saveUser(user);
        } else {
            User user = new User(firstName, lastName, login, password, new Date(new SimpleDateFormat("yyyy-mm-dd").parse(birth).getTime()), email, phone,roleId,roleService.getRole(roleId));
            userService.saveUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
