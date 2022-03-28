package com.comp.services;

import com.comp.models.BookIssue;
import com.comp.repository.BookIssueRepository;
import com.comp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookIssueService {
    @Autowired
    private BookIssueRepository bookIssueRepository;
    public List<BookIssue> listAllBook() {
        return bookIssueRepository.findAll();
    }
    public void saveBook(BookIssue book) {
        bookIssueRepository.save(book);
    }
    public BookIssue getBook(Integer id) {
        return bookIssueRepository.findById(id).get();
    }
    public void deleteBook(Integer id) {
        bookIssueRepository.deleteById(id);
    }
}

