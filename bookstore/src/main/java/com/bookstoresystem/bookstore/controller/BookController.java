package com.bookstoresystem.bookstore.controller;

//import ch.qos.logback.core.model.Model;
import com.bookstoresystem.bookstore.entity.Book;
import com.bookstoresystem.bookstore.entity.MyBookList;
import com.bookstoresystem.bookstore.service.BookService;
import com.bookstoresystem.bookstore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


import java.util.*;

@Controller
public class BookController {
    @Autowired
    private BookService service;
    //bookservice object
    //import bookservice from service class

    @Autowired
    private MyBookListService myBookService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister()
    {
        return "bookRegister";

    }
    @GetMapping("/available_books")
    public ModelAndView getAllBook(){
        List<Book>list=service.getAllBook();
//        ModelAndView m=new ModelAndView();
//        m.setViewName("booklist");
//        m.addObject("book",list);
//        return m;
        //instead of writing this lines we use constror here
        return new ModelAndView("bookList","book",list);

    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);// here we pass book object
        return "redirect:/available_books";

    }
//    @GetMapping("/my_books")
//    public String getMyBooks(){
//        return "myBooks";

        @GetMapping("/my_books")
        public String getmyBooks(Model model) {
        List<MyBookList>list=myBookService.getAllMyBooks();
        model.addAttribute("book",list);
            return "myBooks";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id")int id)
    {
        Book b=service.getBookById(id);
        MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";

    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id")int id,Model model){
       Book b= service.getBookById(id);
       model.addAttribute("book",b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/available_books";
    }


}
