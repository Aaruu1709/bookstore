package com.bookstoresystem.bookstore.repository;

import com.bookstoresystem.bookstore.entity.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookList,Integer> {

}
