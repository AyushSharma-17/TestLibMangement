package com.api.book.bootrestbook.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;

@RestController
public class BookController {
    @Autowired
    private BookServices bookService;
   //@RequestMapping(value ="/books", method = RequestMethod.GET)
   @GetMapping("/books")                                                //using GET for getting all the books
   public ResponseEntity<List<Book>> getBooks(){
    List<Book> list = bookService.getAllBooks();                               //if list is empty send status code 404
    if(list.size()<=0){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(list);                                  //otherwise send list of books

    }
    @GetMapping("/books/{id}")                                            //using GET for getting books by id
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
         Book book=bookService.getBookById(id);                                        //check book is their if not null return not found 
         if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
         return ResponseEntity.of(Optional.of(book));                                      //return book 
    }
    @PostMapping("/books")                                               //using POST for storing book record into the list
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try{
         b=this.bookService.addBook(book);
        System.out.println(book);                                        //spring the stored book on console
        return ResponseEntity.of(Optional.of(b));
    }catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
                                                                          //delete book handler using DELETE
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
       try {
        this.bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       } 
    }
                                                                              //update book handler using PUT
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id){
        try {
            this.bookService.updateBook(book,id);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        
    }
}
