package com.comp.controller;

import com.comp.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RoutingController {

    @GetMapping("/")
    public String redirect(Model model){
        if(Session.getUser() == null)
            return "redirect:/index";
        else{
            model.addAttribute("user",Session.getUser());
            switch (Session.getUser().getRole().getRoleName()){
                case "ADMIN": return "redirect:/admin";
                case "WORKER": return "redirect:/admin";
                case "CUSTOMER": return "redirect:/admin";
                default: return "redirect:/users";
            }
        }
    }
}
