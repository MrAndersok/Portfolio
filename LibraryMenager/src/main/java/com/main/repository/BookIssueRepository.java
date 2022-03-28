package com.comp.repository;

import com.comp.models.Book;
import com.comp.models.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {
}

