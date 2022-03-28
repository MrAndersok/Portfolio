package com.comp.services;

import com.comp.models.Book;
import com.comp.models.BookIssue;

import java.util.ArrayList;
import java.util.List;

public class BookAggregatedService {

    private List<Book> allBooks;
    private List<BookIssue> allReservedBooks;
    private List<Book> unavailableBooks;
    private List<Book> availableBooks;


    public BookAggregatedService(List<Book> allBooks, List<BookIssue> allReservedBooks) {

        this.allBooks = allBooks;
        this.allReservedBooks = allReservedBooks;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<BookIssue> getAllReservedBooks() {
        return allReservedBooks;
    }

    public void setAllReservedBooks(List<BookIssue> allReservedBooks) {
        this.allReservedBooks = allReservedBooks;
    }

    public List<Book> getUnavailableBooks() {
        unavailableBooks = new ArrayList<>();
        for(int i=0;i<allReservedBooks.size(); i++){
            if(allReservedBooks.get(i).getReturnDate()==null)
                unavailableBooks.add(allReservedBooks.get(i).getBooksL());
        }
        return unavailableBooks;
    }

    public void setUnavailableBooks(List<Book> unavailableBooks) {
        this.unavailableBooks = unavailableBooks;
    }

    public List<Book> getAvailableBooks() {
        availableBooks = new ArrayList<>();
        getUnavailableBooks();
        for(int i=0;i<allBooks.size(); i++){
            boolean flag = false;
            for(int j=0; j<unavailableBooks.size(); j++)
                if(allBooks.get(i).getId() == unavailableBooks.get(j).getId())
                    flag =true;
            if(!flag)
                availableBooks.add(allBooks.get(i));
        }
        return availableBooks;
    }
}
