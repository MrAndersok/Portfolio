package com.comp.controller;

import com.comp.models.Role;
import com.comp.models.User;
import com.comp.services.RoleService;
import com.comp.services.UserService;
import com.comp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/login")
    public String mainNavigate(Model model){
        if(Session.getUser()!=null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password){
        List<User> users = userService.listAllUser();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)) {
                Session.setUser(users.get(i));
            }
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String loguot(){
        Session.setUser(null);
        return "redirect:/index";
    }
}
