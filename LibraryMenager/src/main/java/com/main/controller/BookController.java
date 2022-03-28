package com.comp.controller;

import com.comp.models.Book;
import com.comp.models.BookIssue;
import com.comp.services.*;
import com.comp.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    BookService bookService;
    @Autowired
    BookIssueService bookIssueService;

    @GetMapping("/booksAvailable")
    public String available(Model model){
        List<Book> books = bookService.listAllBook();
        List<BookIssue> issuedBooks = bookIssueService.listAllBook();
        BookAggregatedService bookAggregatedService = new BookAggregatedService(books,issuedBooks);
        model.addAttribute("books",bookAggregatedService.getAvailableBooks());
        model.addAttribute("status","available");
        model.addAttribute("sessionUser", Session.getUser());
        return "books";
    }

    @GetMapping("/booksUnavailable")
    public String unavailable(Model model){
        List<Book> books = bookService.listAllBook();
        List<BookIssue> issuedBooks = bookIssueService.listAllBook();
        BookAggregatedService bookAggregatedService = new BookAggregatedService(books,issuedBooks);
        model.addAttribute("books",bookAggregatedService.getUnavailableBooks());
        model.addAttribute("status","unavailable");
        model.addAttribute("sessionUser", Session.getUser());
        return "books";
    }

    @GetMapping("/history")
    public String history(@RequestParam("user_id") int id, Model model) {
        List<BookIssue> issuedBooks = bookIssueService.listAllBook();
        List<BookIssue> list = new ArrayList<>();
        for(BookIssue bookIssue:issuedBooks){
            if(bookIssue.getIdUser() == id)
                list.add(bookIssue);
        }
        model.addAttribute("books",list);
        return "history";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id){
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") int id, Model model){
        if(id!=-1) {
            Book book = bookService.getBook(id);
            model.addAttribute("book", book);
        }
        else{
            Book book = new Book();
            book.setId(-1);
            model.addAttribute("book", book);
        }
        return "editBook";
    }

    @GetMapping("/returnBook")
    public String returnBook(@RequestParam("id") int id){
        List<BookIssue> bookIssues = bookIssueService.listAllBook();
        for(int i=0; i<bookIssues.size(); i++){
            if(bookIssues.get(i).getIdBook()==id && bookIssues.get(i).getReturnDate()==null)
            {
                BookIssue bookIssue = bookIssueService.getBook(bookIssues.get(i).getId());
                Date date = new Date(new java.util.Date().getTime());
                bookIssue.setReturnDate(date);
                bookIssueService.saveBook(bookIssue);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/issueBook")
    public String issueBookNavigation(@RequestParam("id") int id, Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book",book);
        model.addAttribute("users",userService.listAllUser());
        return "issueBook";
    }

    @PostMapping("/issueBook")
    public String issueBook(@RequestParam("user_id") int userId,@RequestParam("book_id") int bookId){
        BookIssue bookIssue = new BookIssue(bookId,userId,new Date(new java.util.Date().getTime()),null,bookService.getBook(bookId),userService.getUser(userId));
        bookIssueService.saveBook(bookIssue);
        return "redirect:/";
    }

    @PostMapping("/editBook")
    public String login(@RequestParam("author") String author,
                        @RequestParam("title") String title,
                        @RequestParam("country") String country,
                        @RequestParam("language") String language,
                        @RequestParam("pages") int pages,
                        @RequestParam("year") int year,
                        @RequestParam("id") int id){

        if(id!=-1)
        {
            Book book = bookService.getBook(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCountry(country);
            book.setLanguage(language);
            book.setPages(pages);
            book.setYear(year);
            bookService.saveBook(book);
        }
        else {
            Book temp = new Book(title,author,country,language,pages,year);
            temp.setUsers(new ArrayList<>());
            bookService.saveBook(temp);
        }
        return "redirect:/booksAvailable";
    }
}
