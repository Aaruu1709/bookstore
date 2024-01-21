package com.bookstoresystem.bookstore.repository;

import com.bookstoresystem.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<Book,Integer> {



}
