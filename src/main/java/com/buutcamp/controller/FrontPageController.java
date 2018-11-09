package com.buutcamp.controller;

import com.buutcamp.controller.book.NewBook;
import com.buutcamp.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontPageController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/")
    public String showHome() {
        return "homepage";
    }

    @GetMapping("/admin")
    public String showManager() {
        return "admin-page";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        List<NewBook> list2 = bookDAO.getBorrowedBooks();
        model.addAttribute("bookies",list2);

        List<NewBook> list = bookDAO.getBorrowableBooks();
        model.addAttribute("books",list);

        return "book-page";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}
