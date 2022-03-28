package com.comp.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Book_Issue")
public class BookIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="book_id")
    private int idBook;
    @Column(name="user_id")
    private int idUser;
    private Date issueDate;
    private Date returnDate;

    @ManyToOne()
    @JoinColumn(name="book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book booksL;
    @ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User usersL;

     public BookIssue(){
     }

    public BookIssue(int id, int idBook, int idUser, Date issueDate, Date returnDate) {
        this.id = id;
        this.idBook = idBook;
        this.idUser = idUser;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public BookIssue(int idBook, int idUser, Date issueDate, Date returnDate, Book booksL, User usersL) {
        this.idBook = idBook;
        this.idUser = idUser;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.booksL = booksL;
        this.usersL = usersL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBooksL() {
        return booksL;
    }

    public void setBooksL(Book booksL) {
        this.booksL = booksL;
    }

    public User getUsersL() {
        return usersL;
    }

    public void setUsersL(User usersL) {
        this.usersL = usersL;
    }
}
