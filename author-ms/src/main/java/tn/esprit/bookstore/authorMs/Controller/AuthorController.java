package tn.esprit.bookstore.authorMs.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.authorMs.Entity.Author;
import tn.esprit.bookstore.authorMs.Models.Book;
import tn.esprit.bookstore.authorMs.Service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("authors/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id)
    {
        return authorService.getAuthorById(id);
    }

    @GetMapping
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }



    @GetMapping("books/{id}")
    public List<Book> getBooksByAuthorId(@PathVariable String id)
    {
        return authorService.getBooksByAuthorId(id);
    }




    @PostMapping
    public Author createAuthor(@RequestBody Author author)
    {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author)
    {
        author.setId(id);
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id)
    {
        authorService.deleteAuthor(id);
    }
    @PostMapping("addBook/{id}")
    public Book makeBook(@PathVariable Long id, @RequestBody Book book)
    {
        return authorService.addBook(id,book);
    }
    @GetMapping("/{id}/validate")
    public ResponseEntity<Boolean> validateAuthor(@PathVariable Long id) {
        boolean isValid = authorService.validateAuthor(id);
        return ResponseEntity.ok(isValid);
    }
    @GetMapping("{fName}/{lName}")
    public Author findAuthorByName(@PathVariable String fName,@PathVariable String lName){
        return authorService.findAuthorByName(fName,lName);
    }


}