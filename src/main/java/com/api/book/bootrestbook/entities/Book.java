package com.api.book.bootrestbook.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="book_id")                                                            //book objects and its attributes
    private int id;                                                                  
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Author author;
    
    public Book(int id, String title, Author author) {                                //constuctors for all
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {                                                                   //getter and setter methods
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {                                                                    // default constructor
    }

    @Override
    public String toString() {                                                                    //toString method
        return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }

    
}
