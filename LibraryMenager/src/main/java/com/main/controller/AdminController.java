package com.comp.controller;

import com.comp.models.Book;
import com.comp.models.BookIssue;
import com.comp.models.User;
import com.comp.services.BookIssueService;
import com.comp.services.BookService;
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
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    BookService bookService;
    @Autowired
    BookIssueService bookIssueService;

    @GetMapping("/admin")
    public String mainNavigate(Model model){
        if(Session.getUser()!=null){
            List<Book> books = bookService.listAllBook();
            model.addAttribute("booksCount",books.size());
            int usersCount = 0;
            int workersCount = 0;
            List<User> users = userService.listAllUser();
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getRole().getRoleName().equals("WORKER")){
                    workersCount++;
                }
                else if(users.get(i).getRole().getRoleName().equals("CUSTOMER")){
                    usersCount++;
                }
            }
            List<BookIssue> bookIssues = bookIssueService.listAllBook();
            int countUnavalable=0;
            for(int i=0; i<bookIssues.size(); i++){
                if(bookIssues.get(i).getReturnDate()==null)
                    countUnavalable++;
            };
            model.addAttribute("unavailable",countUnavalable);
            model.addAttribute("available",books.size()-countUnavalable);
            model.addAttribute("usersCount",usersCount);
            model.addAttribute("workersCount",workersCount);
            model.addAttribute("sessionUser",Session.getUser());
            return "admin";
        }
        else
            return "redirect:/";
    }
}
