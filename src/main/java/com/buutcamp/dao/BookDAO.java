package com.buutcamp.dao;

import com.buutcamp.controller.book.NewBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDAO {

    // get a sessionfactory reference
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public void saveBook(NewBook book) {

        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public void borrowBook(NewBook book) {

        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Transactional
    public void returnBook(NewBook book) {

        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    // get exactly those books that the logged in user has borrowed
    @Transactional
    public List<NewBook> getBorrowedBooks() {

        Session session = sessionFactory.getCurrentSession();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Query<NewBook> query = session.createQuery("from NewBook where status='borrowed' and borrowed_by=:username", NewBook.class);
        query.setParameter("username",username);

        return query.getResultList();
    }

    // get those books that are available to borrow
    @Transactional
    public List<NewBook> getBorrowableBooks() {

        Session session = sessionFactory.getCurrentSession();

        Query<NewBook> query = session.createQuery("from NewBook where status='available'", NewBook.class);

        return query.getResultList();
    }

    @Transactional
    public NewBook getBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(NewBook.class, bookId);

    }

}
