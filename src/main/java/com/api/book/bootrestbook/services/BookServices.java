package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component
public class BookServices {
    @Autowired
    private BookRepository bookRepository;
    
    //private static List<Book> list =new ArrayList<>();
    /*static{
        list.add(new Book(1,"java core","xyz"));
        list.add(new Book(2,"java EE","ayush"));
        list.add(new Book(3,"java collection","akhil"));
        list.add(new Book(4,"java container","nikhil"));
    }*/
                                                                        //get all books
    public List<Book> getAllBooks(){
        List<Book> list=(List<Book>)this.bookRepository.findAll();                        //get list of book in the book repository
        return list;
    }
                                                                            //get book by id
    public Book getBookById(int id){

        Book book=null;
       try{
        // book=list.stream().filter(e->e.getId()==id).findFirst().get();        //using stream().filter +lambda expression[java 8 fuctions]
      book= this.bookRepository.findById(id);
    }catch(Exception e){
        e.printStackTrace();
       }
         return book;
    }
                                                                              //adding a book
    public Book addBook(Book b){
       Book result= bookRepository.save(b);
        // list.add(b);
        return result;
    }
                                                                                   //delete a book by its id
    public void deleteBook(int id) {
       // list= list.stream().filter(book ->book.getId()!=id).collect(Collectors.toList());
       bookRepository.deleteById(id);
    }
                                                                                       //update the book by its id
    public void updateBook(Book book, int id) {
         /*  list = list.stream().map(b->{
            if(b.getId()== id){                                                        //id id mathed to given id then list is updated 
                b.setTitle(book.getTitle());                                            // by the new list by map() function of stream API
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());*/
       book.setId(id);
        bookRepository.save(book);
    }
}
