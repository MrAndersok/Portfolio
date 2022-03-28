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
public class IndexController {

    @GetMapping("/index")
    public String mainNavigate(Model model){

        if(Session.getUser() == null)
            return "index";
        else{
            model.addAttribute("user",Session.getUser());
            switch (Session.getUser().getRole().getRoleName()){
                case "ADMIN": return "redirect:/admin";
                case "WORKER": return "redirect:/worker";
                case "CUSTOMER": return "redirect:/users";
                default: return "redirect:/users";
            }
        }
    }
}
