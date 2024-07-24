package tn.esprit.bookstore.bookms.bookqueryms.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.bookstore.bookms.bookqueryms.Service.BookQueryService;
import tn.esprit.bookstore.bookms.dto.BookDto;

import java.util.List;

@RestController
@RequestMapping("/books/queries")
@RequiredArgsConstructor
public class BookQueryController {

    private final BookQueryService bookQueryService;

    /*@GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookQueryService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookQueryService.getAllBooks();
    }
    @GetMapping("author/{id}")
    public List<Book> bookListByAuthor(@PathVariable String id){
        return bookQueryService.bookListByAuthorId(id);
    }
    @GetMapping("single/{id}")
    public Book bookById(@PathVariable Long id)
    {
        return bookQueryService.bookById(id);

    }*/
    ////////////////////////////////////////////////////////////////////
    @GetMapping
    public Page<BookDto> getBooks(int pageNbr, int pageSize){
        return bookQueryService.getBooks(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public BookDto getBook(@PathVariable String id){
        return bookQueryService.getBook(id);
    }
    @GetMapping("author/{id}")
    List<BookDto> allBooksByAuthorId(@PathVariable String id){
        return bookQueryService.getBooksByAuthorId(id);
    }


   /* @GetMapping("name/{name}")
    public BookDto getBook(@PathVariable String name){
        return bookQueryService.getBookByName(name);
    }*/

}

