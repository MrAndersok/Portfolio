package com.comp.services;

import com.comp.models.Book;
import com.comp.repository.BookRepository;
import com.zuckonit.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> listAllBook() {
        return bookRepository.findAll();
    }
    public void saveBook(Book book) {
        bookRepository.saveAndFlush(book);
    }
    public Book getBook(Integer id) {
        return bookRepository.findById(id).get();
    }
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}

