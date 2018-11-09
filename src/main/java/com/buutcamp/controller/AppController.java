package com.buutcamp.controller;

import com.buutcamp.dao.BookDAO;
import com.buutcamp.controller.book.NewBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    //need a reference to the DAO
    @Autowired
    private BookDAO bookDAO;

    //@RequestMapping("/saveBook", method= RequestMethod.POST)
    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") NewBook book, Model model, RedirectAttributes redirectAttributes) {
        book.setBorrowedBy(null);
        bookDAO.saveBook(book);

        // book added, return to admin page
        redirectAttributes.addFlashAttribute(
                "bookAdded", "Book added successfully!");

        return "redirect:/admin";
    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        // get book by its id
        NewBook book = bookDAO.getBook(id);
        // get user info to assign the book to the user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // set the values in the database
        book.setStatus("borrowed");
        book.setBorrowedBy(auth.getName());
        bookDAO.borrowBook(book);

        redirectAttributes.addFlashAttribute("borrowingSucceeded", "Book borrowed successfully!");

        return "redirect:/books";
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        NewBook book = bookDAO.getBook(id);
        book.setStatus("available");
        book.setBorrowedBy(null);
        bookDAO.returnBook(book);

        redirectAttributes.addFlashAttribute("returningSucceeded", "Book returned successfully! Thank you!");

        return "redirect:/books";
    }

}
















