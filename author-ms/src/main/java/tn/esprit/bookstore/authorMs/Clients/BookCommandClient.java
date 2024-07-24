package tn.esprit.bookstore.authorMs.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.bookstore.authorMs.Models.Book;

@FeignClient(name = "book-command-ms")
public interface BookCommandClient {
    @PostMapping("books/commands")
    Book AddBook(@RequestBody Book book);
}


