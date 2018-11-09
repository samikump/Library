package com.buutcamp.controller.book;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class NewBook {

    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    @Column(name="book_id") // column name in database
    @NotNull
    private int id;

    @Column(name="title") // column name in database
    @NotNull
    @Size(min=3)
    private String title;

    @Column(name="author") // column name in database
    @NotNull
    @Size(min=3)
    private String author;

    @Column(name="status") // column name in database
    @NotNull
    private String status;

    @Column(name="borrowed_by") // column name in database
    private String borrowedBy;

    public NewBook() {
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowedBy() { return borrowedBy; }

    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }

    @Override
    public String toString() {
        return "NewBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", borrowedBy='" + borrowedBy + '\'' +
                '}';
    }
}
