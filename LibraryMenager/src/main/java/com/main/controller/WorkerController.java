package com.comp.controller;

import com.comp.models.Book;
import com.comp.models.BookIssue;
import com.comp.models.User;
import com.comp.services.BookIssueService;
import com.comp.services.UserService;
import com.comp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
public class WorkerController {

    @Autowired
    UserService userService;
    @Autowired
    BookIssueService bookIssueService;

    @GetMapping("/workers")
    public String workers(Model model) {
        if(Session.getUser()!=null && Session.getUser().getRole().getId()==1){
            List<User> workers = new ArrayList<>();
            List<User> users = userService.listAllUser();
            for(int i=0; i<users.size();i++){
                if(users.get(i).getRole().getRoleName().equals("WORKER"))
                    workers.add(users.get(i));
            }
            model.addAttribute("users",workers);
            model.addAttribute("role","Workers");
            model.addAttribute("sessionUser", Session.getUser());
            return "users";
        }
        else return "redirect:/";
    }
    @GetMapping("/editWorker")
    public String editWorker(@RequestParam("id") int id, Model model){
        if(Session.getUser()!=null && Session.getUser().getRole().getId()==1){
            if(id!=-1) {
                User user = userService.getUser(id);
                model.addAttribute("user", user);
            }
            else{
                User user = new User();
                user.setId(-1);
                model.addAttribute("user", user);
            }
            model.addAttribute("role_id",2);
            model.addAttribute("user_id",id);

            return "editUser";
        }
        else return "redirect:/";
    }

}
