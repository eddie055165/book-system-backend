package com.booksystemback.book_system_back.repository;

import com.booksystemback.book_system_back.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByName(String name);
}